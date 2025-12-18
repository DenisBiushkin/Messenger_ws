package com.example.messanger.presentation.chat_feature.model

import androidx.compose.ui.graphics.Color


data class ChatUserUi(
    val id: String,
    val name: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: String? = null,
    val userColor: Color? = null // Цвет пользователя для аватара
) {
    // Автоматический цвет для аватара, если не указан
    fun getAvatarColor(): Color {
        return userColor ?: when (id.hashCode() % 6) {
            0 -> Color(0xFF7E57C2) // Фиолетовый
            1 -> Color(0xFF2196F3) // Синий
            2 -> Color(0xFF4CAF50) // Зеленый
            3 -> Color(0xFFE91E63) // Розовый
            4 -> Color(0xFFFF9800) // Оранжевый
            else -> Color(0xFF9C27B0) // Пурпурный
        }
    }

    // Буква для аватара
    val avatarInitial: String get() = name.take(1).uppercase()
}