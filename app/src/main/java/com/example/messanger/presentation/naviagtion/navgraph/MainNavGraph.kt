package com.example.messanger.presentation.naviagtion.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messanger.presentation.chats_list_feature.screens.ChatScreen
import com.example.messanger.presentation.chats_list_feature.screens.ChatsListWrapperScreen
import com.example.messanger.presentation.naviagtion.routes.MainScreen
import com.example.messanger.presentation.profile_feature.screens.ProfileScreenFull


@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.Chat.route
    ){
        composable(route = MainScreen.Chat.route) {
            ChatsListWrapperScreen()
        }

        composable(route = MainScreen.Profile.route) {
            ProfileScreenFull(
                modifier =modifier,
                onLogout = onLogout
            )
        }
    }
}