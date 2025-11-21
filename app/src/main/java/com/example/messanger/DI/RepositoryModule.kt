package com.example.messanger.DI

import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.repository.AuthRepositoryImpl
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
        authDataStoreManager: AuthDataStoreManager
    ): AuthRepository {
        // Создаем AuthApi напрямую без DI
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.NETWORK_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val authApi = retrofit.create(AuthApi::class.java)

        return AuthRepositoryImpl(authApi, authDataStoreManager)
    }
}