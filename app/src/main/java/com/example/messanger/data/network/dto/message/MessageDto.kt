package com.example.messanger.data.network.dto.message

data class MessageDto(
    val id: Int,
    val chat_id: Int,
    val user_id: Int,
    val type: String,
    val message: String,
    val meta: Any? = null,
    val reply_to: Any? = null,
    val files: List<Int> = emptyList(),
    val created_at: String,
)