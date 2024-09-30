package com.straccion.gamermvvmapp.presentation.screens.my_post.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.domain.model.Post

@Composable
fun MyPostsContent(
    navController: NavHostController,
    posts: List<Post>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        items(
            items = posts
        ){ posts ->
            MyPostsCard(navController, post = posts)
        }
    }
}