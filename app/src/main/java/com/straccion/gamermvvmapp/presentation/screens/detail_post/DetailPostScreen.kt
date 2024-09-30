package com.straccion.gamermvvmapp.presentation.screens.detail_post

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.screens.detail_post.components.DetailPostContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPostScreen(navController: NavHostController, post: String) {
    Scaffold(
        content = {
            DetailPostContent(navController)
        }
    )


}