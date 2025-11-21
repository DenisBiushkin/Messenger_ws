package com.example.messanger.presentation.naviagtion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messanger.presentation.chats_feature.ChatScreen
import com.example.messanger.presentation.profile_feature.ProfileScreen
import com.example.messanger.presentation.register_feature.components.RegisterScreen


@Composable
fun RootNavigationGraph(
    navHostController: NavHostController,
    startDestination :String = Screens.Register.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.AUTH_GRAPH
    ){

        // Граф аутентификации
        authGraph(navHostController)

        // Граф основной
        mainGraph(navHostController, modifier = modifier)

    }
}