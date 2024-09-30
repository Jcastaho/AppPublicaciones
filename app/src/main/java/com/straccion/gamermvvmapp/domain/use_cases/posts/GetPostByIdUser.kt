package com.straccion.gamermvvmapp.domain.use_cases.posts

import com.straccion.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostByIdUser @Inject constructor(private val repository: PostRepository) {
    operator fun invoke(idUser: String) = repository.getPostsByUserId(idUser)
}