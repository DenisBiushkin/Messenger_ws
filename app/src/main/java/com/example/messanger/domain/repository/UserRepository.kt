package com.example.messanger.domain.repository

import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.model.User
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun registerUser(registerUser: RegisterUser): Resource<Unit>
    suspend fun getCurrentUser():  Flow<Resource<User>>

    suspend fun getUserById(id:Int): Flow<Resource<User>>

    suspend fun getUsers(offset:Int = 0,limit:Int = 20):Flow<Resource<List<User>>>

    suspend fun getUsersByName(username:String,offset:Int = 0,limit:Int = 20):Flow<Resource<List<User>>>

}