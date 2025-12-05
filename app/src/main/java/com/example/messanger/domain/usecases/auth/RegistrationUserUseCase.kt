package com.example.messanger.domain.usecases.auth

import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegistrationUserUseCase(
    private val userRepository: UserRepository
) {
    fun execute(
        registerUser: RegisterUser
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        emit(userRepository.registerUser(registerUser))
    }
}