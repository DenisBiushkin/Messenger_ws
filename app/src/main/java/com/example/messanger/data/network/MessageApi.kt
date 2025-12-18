package com.example.messanger.data.network

import com.example.messanger.data.network.dto.message.ChangeMessageRequest
import com.example.messanger.data.network.dto.message.MarkReadDto
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

    //ok
    @GET("/api/chats/{chat}/messages")
    suspend fun getMessageByChatId(
        @Path("chat") chat: Int,
        @Query("offset") offset :Int=0,
        @Query("limit") limit :Int=20
    ): MessageDataDto

    //Send OK
    @POST("/api/chats/{chat}/messages")
    suspend fun sendMessage(
        @Path("chat") chatId :Int,
        @Body request: SendMessageRequest
    )


    //ok
    @PUT("/api/messages/{message}")
    suspend fun changeMessageById(
        @Path("message") id:Int,
        @Body request: ChangeMessageRequest
    )


    //ok
    @DELETE("/api/messages/{message}")
    suspend fun softDeleteMessageById(
        @Path("message") id:Int,
    )

    //пойдет
    @POST("/api/messages/{message}/read")
    suspend fun markMessageReadByMessageId(
       @Path("message") id:Int,
    ): MarkReadDto


}