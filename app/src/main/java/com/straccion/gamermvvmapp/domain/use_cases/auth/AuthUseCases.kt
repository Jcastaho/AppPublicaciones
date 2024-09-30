package com.straccion.gamermvvmapp.domain.use_cases.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val signup: Signup
)