package com.example.messanger.data.network.dto.chat

import com.example.messanger.data.network.dto.user.UserDto
import com.example.messanger.data.network.dto.user.UsersDto

data class UsersInChatDto(
    val data: List<UserDto> = emptyList()
)