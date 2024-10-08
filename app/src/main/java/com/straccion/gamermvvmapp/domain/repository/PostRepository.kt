package com.straccion.gamermvvmapp.domain.repository

import com.straccion.gamermvvmapp.domain.model.Post
import com.straccion.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    fun getPosts(): Flow<Response<List<Post>>>
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
    suspend fun create(post: Post, file: File): Response<Boolean>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(idPost: String): Response<Boolean>
    suspend fun like(idPost: String, idUser: String): Response<Boolean>
    suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean>
}