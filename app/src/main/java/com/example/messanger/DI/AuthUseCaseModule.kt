package com.example.messanger.DI

import com.example.messanger.domain.repository.AuthRepository
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.domain.usecases.auth.RegistrationUserUseCase
import com.example.messanger.domain.usecases.auth.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {


    @Singleton
    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase = SignInUseCase(authRepository)

    @Singleton
    @Provides
    fun provideRegistrationUserUseCase(userRepository: UserRepository): RegistrationUserUseCase = RegistrationUserUseCase(userRepository)
}