package com.straccion.gamermvvmapp.domain.use_cases.posts

import com.straccion.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class DeleteLikePost @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(idPost: String, idUser: String) = repository.deleteLike(idPost, idUser)
}