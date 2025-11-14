package com.example.messanger.presentation.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messanger.presentation.register_feature.components.RegisterScreen


@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination :String = Screens.Register.route
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){
        composable(
            route = Screens.Register.route
        ){
            RegisterScreen()
        }
    }
}