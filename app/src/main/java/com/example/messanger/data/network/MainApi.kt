package com.example.messanger.data.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MainApi {

    @POST("api/register")
    suspend fun registerUser(

    )

    @GET("api/user/profile")
    suspend fun getUser(
        //@Header("Authorization") accessToken:String
    ): UserDto

}