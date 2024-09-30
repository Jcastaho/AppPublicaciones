package com.straccion.gamermvvmapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.presentation.components.DefaultProgressBar
import com.straccion.gamermvvmapp.presentation.screens.posts.PostsViewModel

@Composable
fun GetPost(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()) {
    when (val response = viewModel.postsResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            PostsContent(navController, posts = response.data)
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}