package com.example.messanger.domain.model

data class Chat(
    val id: Int,
    val avatar: Avatar,
    val title: String,
    val messages: List<Message>,
    val type: ChatType,
    val usersId: List<Int>
)
