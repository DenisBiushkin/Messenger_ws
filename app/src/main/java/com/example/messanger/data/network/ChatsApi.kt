package com.example.messanger.data.network

import com.example.messanger.data.network.dto.chat.ChatDto
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.data.network.dto.chat.UsersInChatDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatsApi {


    @GET("/api/chats")
    suspend fun getChats(): ChatDto

    @GET("/api/chats/{chat}/users")
    suspend fun getUsersInChatById(
        @Path("chat") chatId:Int
    ): UsersInChatDto


    //работает но выдает 500
    @POST("/api/chats")
    suspend fun createChat(
        @Body request : CreateChatRequest
    )


}