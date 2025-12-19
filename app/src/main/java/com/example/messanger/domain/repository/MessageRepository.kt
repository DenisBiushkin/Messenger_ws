package com.example.messanger.domain.repository

import com.example.messanger.data.network.dto.message.ChangeMessageRequest
import com.example.messanger.data.network.dto.message.MessageDataDto
import com.example.messanger.domain.model.Message
import com.example.messanger.domain.model.SendMessage
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query

interface MessageRepository {

    suspend fun getMessages(chatId: Int, offset :Int=0, limit :Int=20): Flow<Resource<List<Message>>>

    suspend fun sendMessage(chatId:Int,messageData: SendMessage): Flow<Resource<Unit>>

    suspend fun markMessageReadById(messageId:Int): Flow<Resource<Unit>>

    suspend fun softDeleteMessageById(id:Int ): Flow<Resource<Unit>>

    suspend fun changeMessageById(id:Int, newText:String): Flow<Resource<Unit>>

}