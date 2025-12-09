package com.example.messanger.data.network

import com.example.messanger.data.network.dto.MessageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MessageApi {


    @GET("api/chats/{chat}/messages")
    fun getMessageById(
        @Path("chat") chatId: Int
    ): MessageDto

}