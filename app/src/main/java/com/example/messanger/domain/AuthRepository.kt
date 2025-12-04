package com.example.messanger.domain

import com.example.messanger.data.AuthTokens

interface AuthRepository {

    suspend fun signIn(phone:String,password:String): Boolean

    suspend fun signOut(): Boolean

}