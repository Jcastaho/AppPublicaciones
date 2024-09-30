package com.straccion.gamermvvmapp.domain.use_cases.posts

import com.straccion.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPost @Inject constructor(private val repository: PostRepository) {
    operator fun invoke() = repository.getPosts()
}