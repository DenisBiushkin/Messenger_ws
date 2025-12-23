package com.example.messanger.data.network.dto.chat

import com.example.messanger.data.network.dto.user.AvatarDto
import com.example.messanger.domain.model.Avatar

data class Data(
    val avatar: AvatarDto? = null,
    val id: Int,
    val messages: List<Message>,
    val title: String,
    val type: String,
    val users_id: List<Int>
)