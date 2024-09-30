package com.straccion.gamermvvmapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.screens.posts.components.DeleteLikePost
import com.straccion.gamermvvmapp.presentation.screens.posts.components.GetPost
import com.straccion.gamermvvmapp.presentation.screens.posts.components.LikePost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostScreen(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()) {
    Scaffold (
        content = {
            GetPost(navController)
        }
    )
    LikePost()
    DeleteLikePost()



}