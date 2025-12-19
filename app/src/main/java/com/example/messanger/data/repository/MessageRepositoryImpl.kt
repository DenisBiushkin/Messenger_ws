package com.example.messanger.data.repository

import com.example.messanger.data.mapper.MessageMapper
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.data.network.dto.message.SendMessageRequest
import com.example.messanger.domain.model.Message
import com.example.messanger.domain.model.SendMessage
import com.example.messanger.domain.repository.MessageRepository
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import kotlin.Int

class MessageRepositoryImpl(
    private val messageApi: MessageApi
): MessageRepository {

    override suspend fun getMessages(
        chatId: Int,
        offset: Int,
        limit: Int
    ): Flow<Resource<List<Message>>> = flow{
        try {
            val result = messageApi.getMessageByChatId( chatId, offset, limit).data

            emit(Resource.Success(
                result.map { MessageMapper().fromFirstToSecond(it) }
            ))

        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun sendMessage(chatId:Int,messageData: SendMessage): Flow<Resource<Unit>> = flow {
        try {

            val requestModel =messageData.run{
                SendMessageRequest(
                    files= filesId,
                    message = message,
                    reply_to = replyTo,
                    type = type.name
                )
            }
            val result = messageApi.sendMessage(chatId,requestModel)

            emit(Resource.Success(Unit))

        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun markMessageReadById(messageId: Int): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun softDeleteMessageById(id: Int): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun changeMessageById(
        id: Int,
        newText: String
    ): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }
}