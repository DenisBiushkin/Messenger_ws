package com.example.messanger.domain.model

data class Message(
    val id: Int,
    val chatId: Int,
    val userId: Int,
    val type: MessageType,
    val message: String,
    val filesId: List<Int> = emptyList(),
    val createdAt: String,
)