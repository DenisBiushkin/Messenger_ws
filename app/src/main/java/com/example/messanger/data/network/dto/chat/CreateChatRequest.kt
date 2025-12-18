package com.example.messanger.data.network.dto.chat

data class CreateChatRequest(
    val title: String,
    val type: String,
    val users: List<Int> = emptyList()
)