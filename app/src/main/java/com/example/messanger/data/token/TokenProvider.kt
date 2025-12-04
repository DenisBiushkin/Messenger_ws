package com.example.messanger.data.token

interface TokenProvider {

    suspend fun getAccessToken(): String

    suspend fun refreshTokens(): Boolean

    suspend fun authenticate(phone:String, password:String): Boolean

    suspend fun saveTokensModel(accessToken:String,refreshToken:String)

    suspend fun clearTokens()
}