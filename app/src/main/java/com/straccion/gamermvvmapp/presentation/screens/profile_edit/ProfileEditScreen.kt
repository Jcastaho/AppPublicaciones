package com.straccion.gamermvvmapp.presentation.screens.profile_edit

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.components.DefaultTopBar
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.components.ProfileUpdate
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.components.SaveImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
    ){
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController = navController)
        },
        bottomBar = {}
    )
    SaveImage()
    ProfileUpdate()
}