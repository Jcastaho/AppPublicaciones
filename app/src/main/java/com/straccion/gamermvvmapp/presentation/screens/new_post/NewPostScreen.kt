package com.straccion.gamermvvmapp.presentation.screens.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.components.DefaultButton
import com.straccion.gamermvvmapp.presentation.components.DefaultTopBar
import com.straccion.gamermvvmapp.presentation.screens.new_post.components.NewPost
import com.straccion.gamermvvmapp.presentation.screens.new_post.components.NewPostContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            DefaultTopBar(
                title = "Nuevo Post",
                upAvailable = true,
                navController = navController
            )

        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars),
                text = "PUBLICAR",
                onClick = { viewModel.onNewPost() }
            )
        }
    )
    NewPost()
}