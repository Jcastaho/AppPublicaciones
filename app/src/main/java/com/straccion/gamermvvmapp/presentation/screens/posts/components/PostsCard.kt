package com.straccion.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.domain.model.Post
import com.straccion.gamermvvmapp.presentation.navigation.DetailsScreen
import com.straccion.gamermvvmapp.presentation.screens.posts.PostsViewModel

@Composable
fun PostsCard(
    navController: NavHostController,
    post: Post,
    viewModel: PostsViewModel = hiltViewModel()
){
    Card(
        modifier = Modifier
            .padding(top = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop,
                model = post.image,
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = post.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = post.user?.username ?: "",
                fontSize = 13.sp,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
            ) {
                if (post.likes.contains(viewModel.currentUser?.uid)) {
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                viewModel.deleteLike(post.id, viewModel.currentUser?.uid ?: "")
                            },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = ""
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                viewModel.like(post.id, viewModel.currentUser?.uid ?: "")
                            },
                        painter = painterResource(id = R.drawable.like_outline),
                        contentDescription = ""
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    text = "" + post.likes.size
                )
            }
        }
    }
}