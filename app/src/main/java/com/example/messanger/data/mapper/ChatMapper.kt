package com.example.messanger.data.mapper

import com.example.messanger.data.network.dto.chat.ChatDto
import com.example.messanger.data.network.dto.chat.Data
import com.example.messanger.data.network.dto.user.AvatarDto
import com.example.messanger.domain.mapper.Mapper
import com.example.messanger.domain.model.Avatar
import com.example.messanger.domain.model.Chat
import com.example.messanger.domain.model.ChatType

class ChatMapper: Mapper<Data, Chat> {
    override fun fromFirstToSecond(first: Data): Chat {
        return first.run {
            Chat(
                id= id,
                avatar = avatar?.let {
                    AvatarMapper().fromFirstToSecond(it as AvatarDto)
                } ?: Avatar(),
                title = title ?: "<Неопределен>",
                messages = emptyList(),
                type = when(type.lowercase()) {
                    "private" -> ChatType.PRIVATE
                    "group" -> ChatType.GROUP
                    else -> {
                        ChatType.GROUP
                    } },
                usersId = users_id
            )
        }
    }

    override fun fromSecondToFirst(second: Chat):  Data {
        throw RuntimeException("У  тебя маппер не дописан дурында")
    }
}