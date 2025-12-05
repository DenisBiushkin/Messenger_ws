package com.example.messanger.domain.usecases.auth

import com.example.messanger.data.token.TokenProvider
import com.example.messanger.util.Resource

class LogoutUserUseCase(
    private val tokenProvider: TokenProvider
) {
    suspend fun execute(): Unit{
        tokenProvider.clearTokens()
    }
}