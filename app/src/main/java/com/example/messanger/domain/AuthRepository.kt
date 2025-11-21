package com.example.messanger.domain

import com.example.messanger.data.AuthTokens

interface AuthRepository {

    //заменить потом датакласс
    suspend fun login(username:String,password:String): AuthTokens

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken():String

    suspend fun updateToken(): Boolean

    suspend fun logout()

}