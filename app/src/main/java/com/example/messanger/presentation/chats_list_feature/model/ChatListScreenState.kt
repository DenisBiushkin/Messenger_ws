package com.example.messanger.presentation.chats_list_feature.model

data class ChatListScreenState(
    val chats: List<ChatListItemUi> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val isEmpty: Boolean = false
)