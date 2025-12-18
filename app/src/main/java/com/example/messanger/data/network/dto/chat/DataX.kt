package com.example.messanger.data.network.dto.chat

import com.example.messanger.data.network.dto.message.MessageDto
import com.example.messanger.data.network.dto.user.AvatarDto

data class DataX(
    val avatar: AvatarDto? = null,
    val id: Int,
    val messages: List<MessageDto>,
    val title: String,
    val type: String,
    val users_id: List<Int>
)