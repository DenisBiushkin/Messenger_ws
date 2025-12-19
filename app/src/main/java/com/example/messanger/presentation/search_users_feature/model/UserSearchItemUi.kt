package com.example.messanger.presentation.search_users_feature.model

data class UserSearchItemUi(
    val id: String,
    val name: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: String? = null,
    val initials: String = name.take(1).uppercase()
)