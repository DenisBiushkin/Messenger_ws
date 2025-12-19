package com.example.messanger.domain.model

data class SendMessage(
    val filesId: List<Int> = emptyList(),
    val message: String,
    val replyTo: String? = null,
    val type: MessageType
)
