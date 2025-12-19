package com.example.messanger.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val createdAt: String,
    val avatars: List<Avatar> = emptyList()
) {
    val formattedPhone: String
        get() = phone

    val hasAvatar: Boolean
        get() = avatars.isNotEmpty()
    
    val mainAvatar: Avatar?
        get() = avatars.firstOrNull()
}