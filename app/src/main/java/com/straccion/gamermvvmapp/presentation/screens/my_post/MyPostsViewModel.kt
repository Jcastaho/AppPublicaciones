package com.straccion.gamermvvmapp.presentation.screens.my_post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.straccion.gamermvvmapp.domain.model.Post
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.straccion.gamermvvmapp.domain.use_cases.posts.PostsUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUsesCases: PostsUsesCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postsUsesCases.getPostByIdUser(currentUser?.uid ?: "").collect() { response ->
            postsResponse = response
        }
    }

    fun delete(idPost: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = postsUsesCases.deletePost(idPost)
        deleteResponse = result
    }
}