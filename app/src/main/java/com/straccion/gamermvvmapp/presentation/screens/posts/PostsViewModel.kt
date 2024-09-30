package com.straccion.gamermvvmapp.presentation.screens.posts

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
class PostsViewModel @Inject constructor(
    private val postsUsesCases: PostsUsesCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)
    var deletelikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser = authUseCases.getCurrentUser()

    init {
        getPosts()
    }
    fun like(idPost: String, idUser: String) = viewModelScope.launch {
        likeResponse = Response.Loading
        val result = postsUsesCases.likePost(idPost, idUser)
        likeResponse = result
    }
    fun deleteLike(idPost: String, idUser: String) = viewModelScope.launch {
        deletelikeResponse = Response.Loading
        val result = postsUsesCases.deleteLikePost(idPost, idUser)
        deletelikeResponse = result
    }

    fun getPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postsUsesCases.getPost().collect() { response ->
            postsResponse = response
        }
    }
}