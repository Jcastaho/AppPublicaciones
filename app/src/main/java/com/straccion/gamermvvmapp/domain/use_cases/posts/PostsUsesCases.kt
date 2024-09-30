package com.straccion.gamermvvmapp.domain.use_cases.posts

data class PostsUsesCases(
    val create: CreatePost,
    val getPost: GetPost,
    val getPostByIdUser: GetPostByIdUser,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val likePost: LikePost,
    val deleteLikePost: DeleteLikePost
)
