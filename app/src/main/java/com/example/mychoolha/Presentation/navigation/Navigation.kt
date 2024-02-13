package com.example.mychoolha.Presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mychoolha.Presentation.screens.Profile.home
import com.example.mychoolha.Presentation.screens.LoginSignupFlow.LoginScreen
import com.example.mychoolha.Presentation.screens.LoginSignupFlow.RegisterScreen
import com.example.mychoolha.viewModel.AuthViewModel


@Composable
fun myNavGraph(viewModel: AuthViewModel ,navController: NavHostController)
{
    NavHost(navController = navController, startDestination = "login", enterTransition = {slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left, tween(800))},
       )
    {
        composable("login"){
            LoginScreen(viewModel, navController)
        }
        composable("register"){
            RegisterScreen(viewModel, navController)
        }
        composable("home"){
            home(viewModel)
        }

    }
}