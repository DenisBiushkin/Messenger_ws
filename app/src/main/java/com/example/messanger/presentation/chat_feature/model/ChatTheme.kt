package com.example.messanger.presentation.chat_feature.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ChatTheme(
    // Основные цвета
    val primaryColor: Color = Color(0xFF7E57C2),
    val secondaryColor: Color = Color(0xFF9575CD),
    val backgroundColor: Color = Color(0xFFF8F7FC),
    
    // Цвета сообщений
    val myMessageColor: Color = Color(0xFF8A2BE2),
    val myMessageTextColor: Color = Color.White,
    val otherMessageColor: Color = Color(0xFFF0E6FF),
    val otherMessageTextColor: Color = Color(0xFF333333),
    
    // Дополнительные цвета
    val senderNameColor: Color = Color(0xFF6A1B9A),
    val timestampColor: Color = Color(0xFF888888),
    val onlineIndicatorColor: Color = Color(0xFF34C759),
    
    // Стили сообщений
    val messageCornerRadius: Dp = 20.dp,
    val messageElevation: Dp = 2.dp,
    val maxMessageWidthPercent: Float = 0.8f
)
