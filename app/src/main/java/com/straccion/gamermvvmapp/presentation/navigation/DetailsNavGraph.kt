package com.straccion.gamermvvmapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.straccion.gamermvvmapp.presentation.screens.detail_post.DetailPostScreen
import com.straccion.gamermvvmapp.presentation.screens.new_post.NewPostScreen
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.straccion.gamermvvmapp.presentation.screens.update_post.UpdatePostScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){
        composable(route = DetailsScreen.NewPost.route){
            NewPostScreen(navController = navController)
        }
        composable(
            route = DetailsScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let { user ->
                ProfileEditScreen(navController, user)
            }
        }

        composable(
            route = DetailsScreen.DetailPost.route,
            arguments = listOf(navArgument("post") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let { post ->
                DetailPostScreen(navController, post)
            }
        }
        composable(
            route = DetailsScreen.UpdatePost.route,
            arguments = listOf(navArgument("post") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let { post ->
                UpdatePostScreen(navController, post)
            }
        }
    }
}

sealed class DetailsScreen(val route: String){

    data object NewPost: DetailsScreen("post/new")

    data object ProfileEdit: DetailsScreen("profile_edit/{user}"){
        fun passUser(user: String) = "profile_edit/$user"
    }
    data object DetailPost: DetailsScreen("post/detail/{post}"){
        fun passPost(post: String) = "post/detail/$post"
    }
    data object UpdatePost: DetailsScreen("post/update/{post}"){
        fun passUpdatePost(post: String) = "post/update/$post"
    }
}