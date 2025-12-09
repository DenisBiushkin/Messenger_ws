package com.example.messanger.data.network.dto

data class DataDto(
    val chat_id: Int,
    val created_at: String,
    val files: List<Any>,
    val id: Int,
    val message: String,
    val meta: Any,
    val reply_to: Any,
    val type: String,
    val user_id: Int
)