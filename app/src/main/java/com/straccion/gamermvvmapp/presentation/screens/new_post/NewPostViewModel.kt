package com.straccion.gamermvvmapp.presentation.screens.new_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.domain.model.Post
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.straccion.gamermvvmapp.domain.use_cases.posts.PostsUsesCases
import com.straccion.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.straccion.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsUsesCases: PostsUsesCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(NewPostState())

    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER session
    val currentUser = authUseCases.getCurrentUser()

    fun createPost(post: Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = postsUsesCases.create(post, file!!)
        createPostResponse = result
    }

    fun onNewPost() {
        val post = Post(
            name = state.name,
            description = state.description,
            category = state.category,
            idUser = currentUser?.uid ?: ""
        )
        createPost(post)
    }

    val radioOption = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOVIL", R.drawable.control),
    )
    fun clearForm(){
        state = state.copy(
            name = "",
            description = "",
            category = "",
            image = ""
        )
        createPostResponse = null
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
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
}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)