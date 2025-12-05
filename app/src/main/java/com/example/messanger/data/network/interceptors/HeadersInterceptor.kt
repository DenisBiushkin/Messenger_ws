package com.example.messanger.data.network.interceptors

import com.example.messanger.data.token.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response


class HeadersInterceptor (
    private val tokenProvider: TokenProvider
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        
        //Accept
        requestBuilder.addHeader("Accept", "application/json")
        //Authorization
        val token = runBlocking {
            tokenProvider.getAccessToken()
        }
        if (token.isNotBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer ${token}")
        }
        
        return chain.proceed(requestBuilder.build())
    }
}