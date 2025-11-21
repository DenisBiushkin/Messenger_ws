package com.example.messanger.DI

import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.MainApi
import com.example.messanger.data.network.interceptors.HeadersInterceptor
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
    fun provideRetrofit( okHttpClient:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.NETWORK_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(authRepository: AuthRepository):HeadersInterceptor{
        return HeadersInterceptor(authRepository)
    }
    
    @Provides
    @Singleton
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideAuthApi(retrofit: Retrofit): AuthApi{
//        return retrofit.create(AuthApi::class.java)
//    }
}