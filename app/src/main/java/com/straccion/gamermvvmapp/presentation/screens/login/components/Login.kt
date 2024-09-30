package com.straccion.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.presentation.components.DefaultProgressBar
import com.straccion.gamermvvmapp.presentation.navigation.Graph
import com.straccion.gamermvvmapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){

    when (val loginResponse = viewModel.loginResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = Graph.HOME){
                    //eliminar historial de pantallas
                    popUpTo(Graph.AUTHENTICATION){
                        inclusive = true
                    }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}