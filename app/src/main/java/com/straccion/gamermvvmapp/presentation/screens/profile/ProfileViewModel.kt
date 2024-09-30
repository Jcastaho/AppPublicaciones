package com.straccion.gamermvvmapp.presentation.screens.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.straccion.gamermvvmapp.domain.model.User
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.straccion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var userData by mutableStateOf(User())
        private set
    val currentUser = authUseCases.getCurrentUser()

    init {
        getUserById()
    }


    private fun getUserById() = viewModelScope.launch {
        Log.d("getUserById", "Iniciando la función getUserById")

        usersUseCases.getUserById(currentUser!!.uid).collect(){ data ->
            Log.d("getUserById", "Datos recibidos: $data")
            userData = data
        }
    }

    fun logout() {
        authUseCases.logout()
    }
}