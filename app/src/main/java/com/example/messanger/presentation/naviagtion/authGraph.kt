package com.example.messanger.presentation.naviagtion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.messanger.presentation.login_feature.LoginScreen
import com.example.messanger.presentation.register_feature.components.RegisterScreen

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screens.Register.route,
        route = NavRoutes.AUTH_GRAPH
    ) {

        composable(
            route = Screens.Register.route
        ){
            RegisterScreen()
        }
        composable(Screens.Register.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Переходим в основное приложение
                    navController.navigate(NavRoutes.MAIN_GRAPH) {
                        popUpTo(NavRoutes.AUTH_GRAPH) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToRegistration = {
                    //navController.navigate(Screens.Registration.route)
                },
                onNavigateToForgotPassword = {
                    //navController.navigate(Screens.ForgotPassword.route)
                }
            )
        }

//
//        composable(Screen.Registration.route) {
//            RegistrationScreen(
//                onRegistrationSuccess = {
//                    // После успешной регистрации переходим в основное приложение
//                    navController.navigate(NavRoutes.MAIN_GRAPH) {
//                        popUpTo(NavRoutes.AUTH_GRAPH) {
//                            inclusive = true
//                        }
//                    }
//                },
//                onNavigateToLogin = {
//                    navController.popBackStack()
//                }
//            )
//        }
//
//        composable(Screen.ForgotPassword.route) {
//            ForgotPasswordScreen(
//                onBack = {
//                    navController.popBackStack()
//                },
//                onPasswordReset = {
//                    navController.popBackStack()
//                }
//            )
//        }
    }
}