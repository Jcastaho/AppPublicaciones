package com.straccion.gamermvvmapp.domain.use_cases.auth

import com.straccion.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}