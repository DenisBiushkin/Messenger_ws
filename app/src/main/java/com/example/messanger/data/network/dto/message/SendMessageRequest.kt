package com.example.messanger.data.network.dto.message

data class SendMessageRequest(
    val files: List<Int> = emptyList(),
    val message: String,
    val reply_to: String? = null,
    val type: String
)