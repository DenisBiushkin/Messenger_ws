package com.example.messanger.data.repository

import android.util.Log

import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.repository.AuthRepository
import com.example.messanger.util.Constants
import java.io.IOException


class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenProvider: TokenProvider
): AuthRepository {

    override suspend fun signIn(phone: String, password: String): Boolean {
        try {
            val token =authApi.getAuthToken(
                grantType = Constants.NETWORK_GRANT_TYPE_GET_TOKEN,
                clientId = Constants.NETWORK_CLIENT_ID,
                clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                username =    phone,
                password = password,
                scope =  Constants.NETWORK_SCOPE
            )
            tokenProvider.saveTokensModel(token.access_token,token.refresh_token)
            Log.d(Constants.TAG,"Получен Токен AuthRepository  ${token.access_token}")
            return true;
        }catch (io: Exception){
            Log.d(Constants.TAG,"Ошибка входа AuthRepository  ${io.toString()}")

            return false
        }
    }

    override suspend fun signOut(): Boolean {
        tokenProvider.clearTokens()
        return true
    }
}