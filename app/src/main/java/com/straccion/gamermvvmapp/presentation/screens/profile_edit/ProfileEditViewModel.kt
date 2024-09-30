package com.straccion.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.model.User
import com.straccion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.straccion.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.straccion.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userUsesCases: UsersUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var file: File? = null

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set


    val resultingActivityHandler = ResultingActivityHandler()

    init {
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch {
        if (file != null){
            saveImageResponse = Response.Loading
            val result = userUsesCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/")
        if (result != null) {
            file = ComposeFileProvider.createFileFromuri(context, result)
            state = state.copy(image = result.toString())
        }

    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }


    fun onUpdate(url: String) {
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = userUsesCases.update(user)
        updateResponse = result
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun validateUsername() {
        //valida si es un usuario valido
        if (state.username.length >= 5) {
            usernameErrMsg = ""
        } else {
            usernameErrMsg = "Al menos 5 caracteres"
        }
    }
}