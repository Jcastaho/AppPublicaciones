package com.straccion.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.domain.model.Post

@Composable
fun PostsContent(
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
            PostsCard(navController, post = posts)
        }
    }
}