package com.example.messanger.presentation.naviagtion.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Chat : MainScreen("chat_screen", "Чат", Icons.Default.Email)
    object Profile : MainScreen("profile_screen", "Профиль", Icons.Default.Person)
    object Settings : MainScreen("settings_screen", "Настройки", Icons.Default.Settings)
}