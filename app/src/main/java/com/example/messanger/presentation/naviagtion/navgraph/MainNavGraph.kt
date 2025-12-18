package com.example.messanger.presentation.naviagtion.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.messanger.presentation.chat_feature.screens.ChatScreenWrapper
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
        startDestination = MainScreen.ListChat.route
    ){
        composable(route = MainScreen.ListChat.route) {
            ChatsListWrapperScreen(
                modifier = modifier,
                onChatClick = {
                    chatId->
                    navController.navigate(MainScreen.Chat.createRoute("123"))
                }
            )
        }

        composable(route = MainScreen.Profile.route) {
            ProfileScreenFull(
                modifier =modifier,
                onLogout = onLogout
            )
        }
        composable(
            route= MainScreen.Chat.route,
            arguments = listOf(
                navArgument(MainScreen.Chat.Args.CHAT_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )

        ){ navBackStackEntry ->
            ChatScreenWrapper(
                navController = navController
            )
        }

    }
}