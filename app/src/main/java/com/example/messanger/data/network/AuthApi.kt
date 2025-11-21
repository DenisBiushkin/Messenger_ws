package com.example.messanger.data.network

import com.example.messanger.data.network.dto.TokenDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getAuthToken(
        @Field("grant_type") grantType:String,
        @Field("client_id") clientId:String ,
        @Field("client_secret") clientSecret:String ,
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("scope") scope:String
    ): TokenDto

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getRefreshAuthToken(
        @Field("grant_type") grantType:String,
        @Field("client_id") clientId:String ,
        @Field("client_secret") clientSecret:String ,
        @Field("refresh_token") refreshToken:String,
    ): TokenDto

}