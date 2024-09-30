package com.straccion.gamermvvmapp.presentation.screens.update_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsUsesCases: PostsUsesCases,
    private val authUseCases: AuthUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(UpdatePostState())

    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)
    //USER session
    val currentUser = authUseCases.getCurrentUser()

    init {
        state = state.copy(
            name = post.name,
            description = post.description,
            image = post.image,
            category = post.category
        )
    }

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postsUsesCases.updatePost(post, file)
        updatePostResponse = result
    }

    fun onUpdatePost() {
        val post = Post(
            id = post.id,
            name = state.name,
            image = post.image,
            description = state.description,
            category = state.category,
            idUser = currentUser?.uid ?: ""
        )
        updatePost(post)
    }

    val radioOption = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOVIL", R.drawable.control),
    )
    fun clearForm(){
        updatePostResponse = null
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