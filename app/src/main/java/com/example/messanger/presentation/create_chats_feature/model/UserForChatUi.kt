package com.example.messanger.presentation.create_chats_feature.model

data class UserForChatUi(
    val id: String,
    val name: String,
    val avatarUrl: String? = null,
    val initials: String = name.take(1).uppercase(),
    val isSelected: Boolean = false
)