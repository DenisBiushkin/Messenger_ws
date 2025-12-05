package com.example.messanger.domain.usecases.auth

import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.repository.AuthRepository
import com.example.messanger.util.Resource

class SignInUseCase (
    val authRepository: AuthRepository
) {
    suspend fun execute(
        phone:String,
        password:String
    ): Resource<Unit> {
        if (!authRepository.signIn(phone,password))
            return Resource.Error(message = "")
        return Resource.Success(Unit)
    }
}