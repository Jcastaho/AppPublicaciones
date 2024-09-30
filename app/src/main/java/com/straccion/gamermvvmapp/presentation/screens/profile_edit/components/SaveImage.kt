package com.straccion.gamermvvmapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.presentation.components.DefaultProgressBar
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun SaveImage(viewModel: ProfileEditViewModel = hiltViewModel()) {
    when(val response = viewModel.saveImageResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}