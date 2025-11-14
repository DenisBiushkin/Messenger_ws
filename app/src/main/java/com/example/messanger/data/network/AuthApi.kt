package com.example.messanger.data.network

import com.example.messanger.util.Constants.NETWORK_CLIENT_ID
import com.example.messanger.util.Constants.NETWORK_CLIENT_SECRET
import com.example.messanger.util.Constants.NETWORK_GRANT_TYPE
import com.example.messanger.util.Constants.NETWORK_SCOPE
import retrofit2.Response

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("oauth/token")
    fun getAuthToken(
        @Field("grant_type") grantType:String,
        @Field("client_id") clientId:String ,
        @Field("client_secret") clientSecret:String ,
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("scope") scope:String
    ): Response<TokenDto>

}