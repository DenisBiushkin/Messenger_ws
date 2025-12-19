package com.example.messanger.data.mapper

import com.example.messanger.data.network.dto.message.MessageDto
import com.example.messanger.domain.mapper.Mapper
import com.example.messanger.domain.model.Message
import com.example.messanger.domain.model.MessageType

class MessageMapper: Mapper<MessageDto, Message> {

    override fun fromFirstToSecond(first: MessageDto): Message {
       return first.run {
           Message(
               id= id,
               chatId = chat_id,
               userId =user_id,
               type = MessageType.TEXT,
               message = message,
               filesId = files,
               createdAt = created_at
           )
       }
    }

    override fun fromSecondToFirst(second: Message): MessageDto {
        throw RuntimeException("У  тебя маппер не дописан дурында")
    }
}