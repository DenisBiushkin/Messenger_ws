package com.example.messanger.presentation.chats_list_feature.model

import androidx.compose.ui.graphics.Color

data class ChatItemUi(
    val id: String,
    val userName: String,
    val lastMessage: String,
    val avatarColor: Color = getRandomColor(),
    val unreadCount: Int = 0,
    val timestamp: String = "",
    val isOnline: Boolean = false
)

fun getRandomColor(): Color {
    val colors = listOf(
        Color(0xFF4285F4), // Синий
        Color(0xFF34A853), // Зеленый
        Color(0xFFFBBC05), // Желтый
        Color(0xFFEA4335), // Красный
        Color(0xFF8B5CF6)  // Фиолетовый
    )
    return colors.random()
}