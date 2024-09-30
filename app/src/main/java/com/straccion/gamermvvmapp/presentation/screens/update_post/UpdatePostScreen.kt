package com.straccion.gamermvvmapp.presentation.screens.update_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.components.DefaultButton
import com.straccion.gamermvvmapp.presentation.components.DefaultTopBar
import com.straccion.gamermvvmapp.presentation.screens.update_post.components.UpdatePost
import com.straccion.gamermvvmapp.presentation.screens.update_post.components.UpdatePostContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdatePostScreen(navController: NavHostController, post: String, viewModel: UpdatePostViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            DefaultTopBar(
                title = "Editar Post",
                upAvailable = true,
                navController = navController
            )

        },
        content = {
            UpdatePostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars),
                text = "PUBLICAR",
                onClick = { viewModel.onUpdatePost() }
            )
        }
    )
    UpdatePost()
}