package com.example.messanger.domain.repository

import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun registerUser(registerUser: RegisterUser): Resource<Unit>

}