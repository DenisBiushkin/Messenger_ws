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
import com.example.messanger.presentation.create_chats_feature.screens.CreateChatScreenWrapper
import com.example.messanger.presentation.naviagtion.routes.MainScreen
import com.example.messanger.presentation.profile_feature.screens.ProfileScreenFull
import com.example.messanger.presentation.search_users_feature.screens.SearchUserScreenWrapper


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
                },
                onCreateChat = {
                    navController.navigate(MainScreen.CreateChat.route)
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

        composable(route = MainScreen.SearchUsers.route){
            SearchUserScreenWrapper(
                modifier = modifier,
                onChatClick = {
                    userId->
                },
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        composable(route= MainScreen.CreateChat.route){
            CreateChatScreenWrapper(
                modifier = modifier,
                onBackClick = {

                },
                onChatCreated = {
                    chatId->
                }
            )
        }

    }
}