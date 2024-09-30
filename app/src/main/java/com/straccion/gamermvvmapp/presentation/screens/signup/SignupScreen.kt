package com.straccion.gamermvvmapp.presentation.screens.signup

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.components.DefaultTopBar
import com.straccion.gamermvvmapp.presentation.screens.signup.components.SignUp
import com.straccion.gamermvvmapp.presentation.screens.signup.components.SignupContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            SignupContent(navController)
        },
        bottomBar = {}
    )
    SignUp(navController = navController)
}