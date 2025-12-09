package com.example.messanger.DI

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.network.interceptors.HeadersInterceptor
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideMainApi( okHttpClient:OkHttpClient): UserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(UserApi::class.java)
    }
    @Provides
    @Singleton
    fun provideChatsApi( okHttpClient:OkHttpClient): ChatsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ChatsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageApi( okHttpClient:OkHttpClient): MessageApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MessageApi::class.java)
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