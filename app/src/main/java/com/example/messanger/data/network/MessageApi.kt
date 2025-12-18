package com.example.messanger.data.network

import com.example.messanger.data.network.dto.message.MessageDataDto
import com.example.messanger.data.network.dto.message.SendMessageRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageApi {


    @GET("/api/chats/{chat}/messages")
    suspend fun getMessageByChatId(
        @Path("chat") chat: Int
    ): MessageDataDto


    @POST("/api/chats/{chat}/messages")
    suspend fun sendMessage(
        @Path("chat") chatId :Int,
        @Body request: SendMessageRequest
    )

}