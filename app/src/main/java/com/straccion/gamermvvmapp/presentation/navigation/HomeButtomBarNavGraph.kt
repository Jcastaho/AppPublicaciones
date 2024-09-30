package com.straccion.gamermvvmapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FormatListNumbered
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.straccion.gamermvvmapp.presentation.screens.my_post.MyPostScreen
import com.straccion.gamermvvmapp.presentation.screens.posts.PostScreen
import com.straccion.gamermvvmapp.presentation.screens.profile.ProfileScreen

@Composable
fun HomeButtomBarNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeButtomBarScreen.Posts.route
    ) {
        composable(route = HomeButtomBarScreen.Posts.route){
            PostScreen(navController)
        }
        composable(route = HomeButtomBarScreen.MyPost.route){
            MyPostScreen(navController)
        }
        composable(route = HomeButtomBarScreen.Profile.route){
            ProfileScreen(navController)
        }

        detailsNavGraph(navController)
    }
}

sealed class HomeButtomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Posts: HomeButtomBarScreen(
        route = "posts",
        title = "Publicaciones",
        icon = Icons.Default.FormatListNumbered
    )
    data object MyPost: HomeButtomBarScreen(
        route = "my_post",
        title = "Mis Publicaciones",
        icon = Icons.Outlined.FormatListNumbered
    )
    data object Profile: HomeButtomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}