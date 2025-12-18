package com.example.messanger.data.network.dto.chat

import com.example.messanger.data.network.dto.util.MetaDto

data class ChatDto(
    val data: List<Data>,
    val meta: MetaDto
)