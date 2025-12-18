package com.example.messanger.data.network.dto.message

import com.example.messanger.data.network.dto.util.MetaDto

data class MessageDataDto(
    val data: List<MessageDto> = emptyList(),
    val meta: MessageMetaDto
)