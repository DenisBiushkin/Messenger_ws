package com.example.messanger.domain.usecases.auth

import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.repository.UserRepository

class RegistrationUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(
        registerUser: RegisterUser
    ){

    }
}