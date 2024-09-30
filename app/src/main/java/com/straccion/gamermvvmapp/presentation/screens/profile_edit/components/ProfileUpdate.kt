package com.straccion.gamermvvmapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.presentation.components.DefaultProgressBar
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun ProfileUpdate(viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val updateResponse = viewModel.updateResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success -> {
            Toast.makeText(LocalContext.current, "Datos actualizados", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
        }
        else -> {}
    }
}