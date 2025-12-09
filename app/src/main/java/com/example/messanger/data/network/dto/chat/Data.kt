package com.example.messanger.data.network.dto.chat

data class Data(
    val avatar: Any,
    val id: Int,
    val messages: List<Message>,
    val title: String,
    val type: String,
    val users_id: List<Int>
)