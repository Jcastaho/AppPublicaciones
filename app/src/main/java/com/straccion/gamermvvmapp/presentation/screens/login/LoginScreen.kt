package com.straccion.gamermvvmapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.straccion.gamermvvmapp.presentation.screens.login.components.Login
import com.straccion.gamermvvmapp.presentation.screens.login.components.LoginBottomBar
import com.straccion.gamermvvmapp.presentation.screens.login.components.LoginContent
import com.straccion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = {},
        content = { LoginContent(navController) },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )
    //manejar el estado de la peticion de login
    Login(navController = navController)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    GamerMVVMAppTheme() {
        LoginScreen(rememberNavController())
    }
}