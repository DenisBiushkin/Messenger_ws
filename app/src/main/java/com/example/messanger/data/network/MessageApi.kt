package com.example.messanger.data.network

import com.example.messanger.data.network.dto.message.ChangeMessageRequest
import com.example.messanger.data.network.dto.message.MessageDataDto
import com.example.messanger.data.network.dto.message.SendMessageRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MessageApi {

    //ok  50/50
    @GET("/api/chats/{chat}/messages")
    suspend fun getMessageByChatId(
        @Path("chat") chat: Int,
        @Query("offset") offset :Int? = null,
        @Query("limit") limit :Int? = null
    ): MessageDataDto

//not tested
    //hz
    @POST("/api/chats/{chat}/messages")
    suspend fun sendMessage(
        @Path("chat") chatId :Int,
        @Body request: SendMessageRequest
    )

    //not tested
    //hz
    @PUT("/api/messages/{message}")
    suspend fun changeMessageById(
        @Path("message") id:Int,
        @Body body: ChangeMessageRequest
    )

    //not tested
    //hz
    @DELETE("/api/messages/{message}")
    suspend fun softDeleteMessageById(
        @Path("message") id:Int,
    )


}