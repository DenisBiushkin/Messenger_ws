package com.example.messanger.presentation.naviagtion

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.messanger.presentation.chats_feature.ChatScreen
import com.example.messanger.presentation.profile_feature.ProfileScreen
import com.example.messanger.presentation.register_feature.components.RegisterScreen

fun NavGraphBuilder.mainGraph(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Screens.Chat.route,
        route = NavRoutes.MAIN_GRAPH
    ) {

        composable(Screens.Chat.route) {
            ChatScreen(modifier)
        }

        composable(Screens.Profile.route) {
           ProfileScreen()
        }
        
//        composable(Screen.Profile.route) {
//            ProfileScreen(
//                onLogout = {
//                    navController.navigate(NavRoutes.AUTH_GRAPH) {
//                        popUpTo(NavRoutes.MAIN_GRAPH) {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
    }
}