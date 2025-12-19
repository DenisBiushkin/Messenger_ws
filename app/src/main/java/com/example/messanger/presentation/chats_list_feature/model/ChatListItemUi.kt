package com.example.messanger.presentation.chats_list_feature.model

import androidx.compose.ui.graphics.Color
import kotlin.math.absoluteValue

data class ChatListItemUi(
    val id: String,
    val userName: String,
    val lastMessage: String,
    val avatarColor: Color = getRandomColor(userName), // Генерируем цвет на основе имени
    val unreadCount: Int = 0,
    val timestamp: String = "",
    val isOnline: Boolean = false,
    val avatarInitial: String = userName.take(1).uppercase(),
    val isGroup: Boolean = false,
    val participantCount: Int = if (isGroup) 0 else 1
)
    fun getRandomColor(seed: String): Color {
        val colors = listOf(
            Color(0xFF4285F4), // Синий
            Color(0xFF34A853), // Зеленый
            Color(0xFFFBBC05), // Желтый
            Color(0xFFEA4335), // Красный
            Color(0xFF8B5CF6), // Фиолетовый
            Color(0xFF00BCD4), // Голубой
            Color(0xFF9C27B0), // Пурпурный
            Color(0xFFFF9800), // Оранжевый
        )
        val hash = seed.hashCode()
        val index = (hash % colors.size).absoluteValue
        return colors[index]
    }



