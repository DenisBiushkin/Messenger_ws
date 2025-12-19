package com.example.messanger.DI

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.repository.AuthRepositoryImpl
import com.example.messanger.data.repository.ChatRepositoryImpl
import com.example.messanger.data.repository.MessageRepositoryImpl
import com.example.messanger.data.repository.UserRepositoryImpl
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.repository.AuthRepository
import com.example.messanger.domain.repository.ChatRepository
import com.example.messanger.domain.repository.MessageRepository
import com.example.messanger.domain.repository.UserRepository
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

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi
    ): UserRepository {
        return UserRepositoryImpl(userApi)
    }
    @Provides
    @Singleton
    fun provideChatRepository (
        chatApi: ChatsApi
    ): ChatRepository {
        return ChatRepositoryImpl(chatApi)
    }
    @Provides
    @Singleton
    fun provideMessageRepository(
        messageApi: MessageApi
    ): MessageRepository {
        return MessageRepositoryImpl(messageApi)
    }
}