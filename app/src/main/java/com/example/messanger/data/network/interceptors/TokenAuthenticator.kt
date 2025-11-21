package com.example.messanger.data.network.interceptors

import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.data.network.AuthApi
import com.example.messanger.domain.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val authRepository: AuthRepository
) : Authenticator {
    
    override fun authenticate(route: Route?, response: Response): Request? {
        // Для корутин используем runBlocking, но лучше перейти на suspend authenticator

        return runBlocking {
            try {
                authRepository.updateToken()
                val newToken = authRepository.getAccessToken()
                if (newToken != null) {
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