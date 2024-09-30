package com.straccion.gamermvvmapp.domain.use_cases.posts

import com.straccion.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class LikePost @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(idPost: String, idUser: String) = repository.like(idPost, idUser)
}