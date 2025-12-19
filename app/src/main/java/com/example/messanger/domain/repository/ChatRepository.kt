package com.example.messanger.domain.repository

import com.example.messanger.domain.model.Chat
import com.example.messanger.domain.model.ChatType
import com.example.messanger.domain.model.User
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatRepository {

    suspend fun getChats( offset :Int = 0, limit :Int = 20): Flow<Resource<List<Chat>>>

    suspend fun getUsersInChatById(chatId:Int): Flow<Resource<List<User>>>

    //????
    suspend fun createChat(title: String, type: ChatType, usersId: List<Int> ): Flow<Resource<Int>>

    suspend fun getUnreadCountByChat(chatId:Int):Flow<Resource<Int>>

    suspend fun softDeleteChatById(id: Int) :Flow<Resource<Unit>>
}