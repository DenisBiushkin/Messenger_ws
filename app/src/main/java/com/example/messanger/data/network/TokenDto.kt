package com.example.messanger.data.network

data class TokenDto(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String
)