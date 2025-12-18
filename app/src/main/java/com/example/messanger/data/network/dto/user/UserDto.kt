package com.example.messanger.data.network.dto.user

data class UserDto(
    val id: Int,
    val name: String,
    val phone: String,
    val created_at: String,
    val avatar: List<AvatarDto> = emptyList(),
)
