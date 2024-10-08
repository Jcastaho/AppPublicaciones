package com.straccion.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    //Login
    var state by mutableStateOf(LoginState())
        private set
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set


    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    var isEnableLoginButton = false

    fun enabledLoginButton(){
        isEnableLoginButton = isEmailValid && isPasswordValid
    }

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }
    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun validateEmail() {
        //valida si es un email valido
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }
        enabledLoginButton()
    }

    fun validatePassword() {
        //valida si es un email valido
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Escriba al menos 6 caracteres"
        }
        enabledLoginButton()
    }
}