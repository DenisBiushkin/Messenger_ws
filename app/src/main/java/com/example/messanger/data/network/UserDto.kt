package com.example.messanger.data.network

data class UserDto(
    val id: Int,
    val name: String,
    val phone: String,
    val created_at: String,
    val avatars: List<AvatarDto> = emptyList(),
)
data class AvatarDto(
    val id: Int,
    val original_name: String,
    val url: String,
    val created_at: String,
)