package com.example.messanger.data.network


import com.example.messanger.data.network.dto.chat.ChangeTitleRequest
import com.example.messanger.data.network.dto.chat.ChatDto
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.data.network.dto.chat.UsersInChatDto
import com.example.messanger.presentation.naviagtion.routes.MainScreen
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatsApi {


    // 50/50
    @GET("/api/chats")
    suspend fun getChats(
        @Query("offset") offset :Int? = null,
        @Query("limit") limit :Int? = null
    ): ChatDto

    //not tested
    @GET("/api/chats/{chat}")
    suspend fun getLastMessages(
        @Path("chat") chatId: Int
    ): ChatDto


    @GET("/api/chats/{chat}/users")
    suspend fun getUsersInChatById(
        @Path("chat") chatId:Int
    ): UsersInChatDto


    //работает но выдает 500
    @POST("/api/chats")
    suspend fun createChat(
        @Body request : CreateChatRequest
    )

    //not tested
    //hz
    @PUT("/api/chats/{chat}")
    suspend fun changeChatName(
        @Body request : ChangeTitleRequest
    )

    //not tested
    //hz
    @DELETE("/api/chats/{chat}")
    suspend fun softDeleteChatById(
        @Path("chat") id: Int
    )


}