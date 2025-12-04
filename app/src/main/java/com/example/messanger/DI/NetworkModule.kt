package com.example.messanger.DI

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.MainApi
import com.example.messanger.data.network.interceptors.HeadersInterceptor
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.AuthRepository
import com.example.messanger.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(headersInterceptor: HeadersInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(tokenProvider: TokenProvider):HeadersInterceptor{
        return HeadersInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    fun provideMainApi( okHttpClient:OkHttpClient): MainApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MainApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi{
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API_AUTH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}