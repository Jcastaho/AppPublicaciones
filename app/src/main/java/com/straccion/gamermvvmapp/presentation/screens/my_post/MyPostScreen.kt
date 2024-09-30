package com.straccion.gamermvvmapp.presentation.screens.my_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.presentation.navigation.DetailsScreen
import com.straccion.gamermvvmapp.presentation.screens.my_post.components.GetPostByIdUser
import com.straccion.gamermvvmapp.presentation.screens.posts.components.GetPost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPostScreen(navController: NavHostController) {
    Scaffold (
        content = {
            GetPostByIdUser(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = { navController.navigate(DetailsScreen.NewPost.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )



}