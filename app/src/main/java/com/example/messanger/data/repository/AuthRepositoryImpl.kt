package com.example.messanger.data.repository

import android.util.Log
import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.data.AuthTokens
import com.example.messanger.data.network.AuthApi
import com.example.messanger.domain.AuthRepository
import com.example.messanger.util.Constants
import kotlinx.coroutines.flow.first
import java.io.IOException


class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val authDataStoreManager: AuthDataStoreManager
): AuthRepository {

    override suspend fun login(
        username: String,
        password: String
    ): AuthTokens {
        val token =authApi.getAuthToken(
                    grantType = Constants.NETWORK_GRANT_TYPE_GET_TOKEN,
                    clientId = Constants.NETWORK_CLIENT_ID,
                    clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                    username = username,
                    password = password,
                    scope =  Constants.NETWORK_SCOPE
        )
        authDataStoreManager.saveTokens(token.access_token,token.refresh_token)
        return AuthTokens(token.access_token,getAccessToken())
    }

    override suspend fun getAccessToken(): String {
       return authDataStoreManager.getTokens().accessToken
    }

    override suspend fun getRefreshToken(): String {
        return authDataStoreManager.getTokens().accessToken
    }

    override suspend fun updateToken(): Boolean{
        val refresh= getRefreshToken();
        if (refresh.isBlank())
            return false
        try {
            val newToken = authApi.getRefreshAuthToken(
                grantType = Constants.NETWORK_GRANT_TYPE_REFRESH_TOKEN,
                clientId = Constants.NETWORK_CLIENT_ID,
                clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                refreshToken = refresh)
            authDataStoreManager.saveTokens(accessToken = newToken.access_token, refreshToken = newToken.refresh_token)
            return true
        }catch (e: IOException){
            Log.d(Constants.TAG,"Ошибка обновления токена")
            return false
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isLoggedIn(): Boolean {
        val tokens = authDataStoreManager.tokens.first()
        return tokens.accessToken.isNotBlank() && tokens.refreshToken.isNotBlank()
    }
}