package com.example.messanger.data.network.dto.chat

data class Message(
    val chat_id: Int,
    val created_at: String,
    val id: Int,
    val message: String,
    val meta: Any,
    val type: String
)