package com.example.messanger.data.repository

import com.example.messanger.data.mapper.ChatMapper
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.domain.model.Chat
import com.example.messanger.domain.model.ChatType
import com.example.messanger.domain.model.User
import com.example.messanger.domain.repository.ChatRepository
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class ChatRepositoryImpl(
    private val chatsApi: ChatsApi
) : ChatRepository{

    override suspend fun getChats(
        offset: Int ,
        limit: Int
    ): Flow<Resource<List<Chat>>> =flow{
        try {
            val result = chatsApi.getChats(limit = limit, offset = offset).data

            emit(Resource.Success(result.map{
                ChatMapper().fromFirstToSecond(it)
            }))

        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun getUsersInChatById(chatId: Int): Flow<Resource<List<User>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createChat(
        title: String,
        type: ChatType,
        usersId: List<Int>
    ): Flow<Resource<Int>> = flow {
        try {
            val result = chatsApi.createChat(CreateChatRequest(
                title = title,
                type= type.name,
                usersId
            ))

            emit(Resource.Success(771))

        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun getUnreadCountByChat(chatId: Int): Flow<Resource<Int>> = flow {
        try {
            val result = chatsApi.getUnreadMessageCountBuChatId(chatId)
            emit(Resource.Success(result.unread_count))
        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun softDeleteChatById(id: Int): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }
}