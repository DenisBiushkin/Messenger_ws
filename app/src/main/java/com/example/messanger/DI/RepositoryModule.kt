package com.example.messanger.DI

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.repository.AuthRepositoryImpl
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.AuthRepository
import com.example.messanger.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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