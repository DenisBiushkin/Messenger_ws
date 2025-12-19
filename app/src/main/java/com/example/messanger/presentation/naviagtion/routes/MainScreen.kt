package com.example.messanger.presentation.naviagtion.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

sealed class MainScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object ListChat : MainScreen("list_chat_screen", "Чат", Icons.Default.Email)
    object Profile : MainScreen("profile_screen", "Профиль", Icons.Default.Person)
    object Settings : MainScreen("settings_screen", "Настройки", Icons.Default.Settings)

    object Chat : MainScreen(
        route = "chat_screen",
        title = "Чат",
        icon = Icons.Default.Email
    ) {
        // Маршрут с параметром chatId
        fun createRoute(chatId: String? = null): String {
            return if (chatId != null) {
                "${baseRoute}?chatId=$chatId"
            } else {
                baseRoute
            }
        }
        val baseRoute = "chat_screen"
        // Ключи аргументов
        object Args {
            const val CHAT_ID = "chatId"
        }
        // Функция для извлечения аргументов
        fun getArgs(navBackStackEntry: NavBackStackEntry): ChatArgs {
            return ChatArgs(
                chatId = navBackStackEntry.arguments?.getString(Args.CHAT_ID),
            )
        }
        data class ChatArgs(
            val chatId: String?,

        )
    }

    object SearchUsers : MainScreen(route = "search_screen","Поиск", Icons.Default.Search)


    object CreateChat: MainScreen(route = "create_chat","Создать чат", icon = Icons.Default.Create)
}