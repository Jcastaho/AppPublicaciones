package com.straccion.gamermvvmapp.domain.repository

import com.straccion.gamermvvmapp.domain.model.Response
import com.straccion.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    suspend fun saveImage(file: File) : Response<String>

    fun getUserById(id: String): Flow<User>
}