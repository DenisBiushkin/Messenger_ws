package com.example.messanger.data.network.interceptors

import com.example.messanger.data.token.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider
) : Authenticator {
    
    override fun authenticate(route: Route?, response: Response): Request? {

        return runBlocking {
            try {
                tokenProvider.refreshTokens()
                val newToken = tokenProvider.getAccessToken()
                if (!newToken.isEmpty()) {
                    response.request.newBuilder()
                        .header("Authorization", "Bearer $newToken")
                        .build()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}