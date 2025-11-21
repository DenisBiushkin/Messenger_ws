package com.example.messanger.data.network.interceptors

import android.util.Log
import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.domain.AuthRepository
import com.example.messanger.util.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

import javax.inject.Inject
import javax.inject.Singleton


class HeadersInterceptor (
    private val authRepository: AuthRepository
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        
        //Accept
        requestBuilder.addHeader("Accept", "application/json")
        //Authorization
        val token = runBlocking {
            authRepository.getAccessToken()
        }
        if (token.isNotBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer ${token}")
        }
        
        return chain.proceed(requestBuilder.build())
    }
}