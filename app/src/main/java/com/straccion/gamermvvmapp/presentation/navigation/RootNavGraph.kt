package com.straccion.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.straccion.gamermvvmapp.presentation.screens.home.HomeScreen
import com.straccion.gamermvvmapp.presentation.screens.login.LoginScreen
import com.straccion.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.straccion.gamermvvmapp.presentation.screens.signup.SignupScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController)

        composable(route = Graph.HOME){
            HomeScreen()
        }
    }
}