package com.example.messanger.presentation.naviagtion.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.messanger.presentation.login_feature.screens.LoginScreen
import com.example.messanger.presentation.login_feature.screens.LoginScreenWrapper
import com.example.messanger.presentation.naviagtion.routes.NavRoutes
import com.example.messanger.presentation.naviagtion.routes.Screens
import com.example.messanger.presentation.register_feature.screens.RegisterScreenFull

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screens.Login.route,
        route = NavRoutes.AUTH_GRAPH
    ) {

        composable(
            route = Screens.Register.route
        ){
            RegisterScreenFull(
                navController=navController,
                onNavigateToLogin = {
                    navController.navigate(Screens.Login.route)
                }
            )
        }
        composable(Screens.Login.route) {

            LoginScreenWrapper(
                onLoginSuccess = {
                    // Переходим в основное приложение
                    navController.navigate(NavRoutes.MAIN_GRAPH) {
                        popUpTo(NavRoutes.AUTH_GRAPH) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToRegistration = {
                    navController.navigate(Screens.Register.route)
                },
                onNavigateToForgotPassword = {

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