package com.example.messanger.domain.repository

interface AuthRepository {

    suspend fun signIn(phone:String,password:String): Boolean

    suspend fun signOut(): Boolean

}