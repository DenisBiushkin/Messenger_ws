package com.example.messanger.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chat_feature.model.ChatTheme

object ChatThemes {
    // Фиолетовая тема (по умолчанию)
    val PurpleTheme = ChatTheme(
        primaryColor = Color(0xFF7E57C2),
        secondaryColor = Color(0xFF9575CD),
        backgroundColor = Color(0xFFF8F7FC),
        myMessageColor = Color(0xFF8A2BE2),
        myMessageTextColor = Color.White,
        otherMessageColor = Color(0xFFF0E6FF),
        otherMessageTextColor = Color(0xFF333333),
        senderNameColor = Color(0xFF6A1B9A),
        timestampColor = Color(0xFF888888)
    )
    
    // Синяя тема (как в Telegram)
    val BlueTheme = ChatTheme(
        primaryColor = Color(0xFF3390EC),
        secondaryColor = Color(0xFF53A5EC),
        backgroundColor = Color(0xFFF0F2F5),
        myMessageColor = Color(0xFF3390EC),
        myMessageTextColor = Color.White,
        otherMessageColor = Color(0xFFFFFFFF),
        otherMessageTextColor = Color(0xFF000000),
        senderNameColor = Color(0xFF3390EC),
        timestampColor = Color(0xFF707579),
        messageCornerRadius = 12.dp,
        messageElevation = 0.5.dp
    )
    
    // Темная тема
    val DarkTheme = ChatTheme(
        primaryColor = Color(0xFFBB86FC),
        secondaryColor = Color(0xFF3700B3),
        backgroundColor = Color(0xFF121212),
        myMessageColor = Color(0xFFBB86FC),
        myMessageTextColor = Color(0xFF000000),
        otherMessageColor = Color(0xFF2D2D2D),
        otherMessageTextColor = Color(0xFFE0E0E0),
        senderNameColor = Color(0xFFBB86FC),
        timestampColor = Color(0xFFB0B0B0)
    )
    
    // Розовая тема
    val PinkTheme = ChatTheme(
        primaryColor = Color(0xFFE91E63),
        secondaryColor = Color(0xFFF8BBD0),
        backgroundColor = Color(0xFFFCE4EC),
        myMessageColor = Color(0xFFE91E63),
        myMessageTextColor = Color.White,
        otherMessageColor = Color(0xFFFFFFFF),
        otherMessageTextColor = Color(0xFF333333),
        senderNameColor = Color(0xFFC2185B),
        timestampColor = Color(0xFF9E9E9E)
    )
}