package com.straccion.gamermvvmapp.presentation.screens.detail_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.straccion.gamermvvmapp.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val data = savedStateHandle.get<String>("post")
    val post = data?.let { Post.fromJson(it) } ?: Post()
}