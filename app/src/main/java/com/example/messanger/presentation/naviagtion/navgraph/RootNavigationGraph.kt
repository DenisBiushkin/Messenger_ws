package com.example.messanger.presentation.naviagtion.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messanger.presentation.naviagtion.MainAppScreen
import com.example.messanger.presentation.naviagtion.routes.NavRoutes


@Composable
fun RootNavigationGraph(
    navHostController: NavHostController,
    startDestination :String = NavRoutes.AUTH_GRAPH
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){

        // Граф аутентификации
        authGraph(navHostController)

        // Граф основной
        composable(route = NavRoutes.MAIN_GRAPH){
            MainAppScreen(
                onLogout = {
                    navHostController.navigate(NavRoutes.AUTH_GRAPH) {
                        popUpTo(NavRoutes.MAIN_GRAPH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}