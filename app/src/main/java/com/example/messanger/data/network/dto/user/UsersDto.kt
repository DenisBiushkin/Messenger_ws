package com.example.messanger.data.network.dto.user

import com.example.messanger.data.network.dto.util.MetaDto

data class UsersDto(
    val data: List<UserDto>,
    val meta: MetaDto
)