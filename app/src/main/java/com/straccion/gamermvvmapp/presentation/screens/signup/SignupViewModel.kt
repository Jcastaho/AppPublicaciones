package com.straccion.gamermvvmapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.model.User
import com.straccion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.straccion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    var isusernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    var isconfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
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

    var user = User()

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set


    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }
    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }
    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignup() {
        user.username = state.username
        user.email = state.email
        user.password = state.password
        signup(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUseCases.signup(user)
        signupResponse = result
    }

    fun enabledLoginButton() {
        isEnableLoginButton = isEmailValid && isPasswordValid
                && isusernameValid && isconfirmPassword
    }

    fun validateConfirmPassword() {
        if (state.password == state.confirmPassword) {
            isconfirmPassword = true
            confirmPasswordErrMsg = ""
        } else {
            isconfirmPassword = false
            confirmPasswordErrMsg = "Las Contraseñas no coinciden"
        }
        enabledLoginButton()
    }

    fun validateUsername() {
        //valida si es un usuario valido
        if (state.username.length >= 5) {
            isusernameValid = true
            usernameErrMsg = ""
        } else {
            isusernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enabledLoginButton()
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

    //firestore
    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }
}