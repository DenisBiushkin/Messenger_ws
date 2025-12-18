package com.example.messanger.data.network


import com.example.messanger.data.network.dto.chat.ChangeTitleRequest
import com.example.messanger.data.network.dto.chat.ChatDto
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.data.network.dto.chat.LastMessagesDto
import com.example.messanger.data.network.dto.chat.UnreadedCountDto
import com.example.messanger.data.network.dto.chat.UsersInChatDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatsApi {


    //ok
    @GET("/api/chats")
    suspend fun getChats(
        @Query("offset") offset :Int = 0,
        @Query("limit") limit :Int = 20
    ): ChatDto

    //ok
    @GET("/api/chats/{chat}")
    suspend fun getChatByIdWithLastMessages(
        @Path("chat") chatId: Int
    ): LastMessagesDto


//ok
    @GET("/api/chats/{chat}/users")
    suspend fun getUsersInChatById(
        @Path("chat") chatId:Int
    ): UsersInChatDto


    //ok
    @POST("/api/chats")
    suspend fun createChat(
        @Body request : CreateChatRequest
    )


    //ok
    @PUT("/api/chats/{chat}")
    suspend fun changeChatName(
        @Path("chat") id: Int,
        @Body request : ChangeTitleRequest
    )


    //ok
    @DELETE("/api/chats/{chat}")
    suspend fun softDeleteChatById(
        @Path("chat") id: Int
    )

//ok
    @GET("/api/chats/{chat}/unread")
    suspend fun getUnreadMessageCountBuChatId(
        @Path("chat") id: Int
    ): UnreadedCountDto


}