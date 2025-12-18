package com.example.messanger.presentation.chat_feature.model

import androidx.compose.ui.graphics.Color

data class MessageUi(
    val id: String,
    val text: String,
    val senderName: String,
    val senderId: String,
    val timestamp: Long,
    val isMine: Boolean = false,
    val isRead: Boolean = false, // Прочитано ли сообщение
    val messageColor: Color? = null, // Кастомный цвет сообщения
    val textColor: Color? = null // Кастомный цвет текста
) {
    // Метод для получения цвета сообщения в зависимости от темы
    fun getMessageColor(theme: ChatTheme): Color {
        return messageColor ?: if (isMine) theme.myMessageColor else theme.otherMessageColor
    }

    // Метод для получения цвета текста
    fun getTextColor(theme: ChatTheme): Color {
        return textColor ?: if (isMine) theme.myMessageTextColor else theme.otherMessageTextColor
    }
}
