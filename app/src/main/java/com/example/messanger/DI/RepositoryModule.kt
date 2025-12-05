package com.example.messanger.DI

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.repository.AuthRepositoryImpl
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        tokenProvider: TokenProvider,
        authApi: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, tokenProvider)
    }
}