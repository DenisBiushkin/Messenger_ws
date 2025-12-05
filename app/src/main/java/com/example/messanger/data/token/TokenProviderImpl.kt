package com.example.messanger.data.token

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.messanger.data.network.AuthApi
import com.example.messanger.util.Constants
import kotlinx.coroutines.flow.first
import java.io.IOException


class TokenProviderImpl(
    private val authApi: AuthApi,
    private val dataStore: DataStore<Preferences>
) : TokenProvider {


    override suspend fun getAccessToken(): String {
        val prefs = dataStore.data.first()
        return prefs[ACCESS_TOKEN].orEmpty()
    }

    override suspend fun hasValidSession(): Boolean {
        val accessToken = getAccessToken()
        val refreshToken = getRefreshToken()

        if (accessToken.isBlank() || refreshToken.isBlank()) {
            Log.d(Constants.TAG, "hasValidSession: один или оба токена пустые")
            return false
        }
        Log.d(Constants.TAG, "hasValidSession: валидная сессия найдена")
        return true
    }


    private suspend fun getRefreshToken():String{
        val prefs = dataStore.data.first()
        return prefs[REFRESH_TOKEN].orEmpty()
    }

    override suspend fun refreshTokens(): Boolean {
        val refresh= getRefreshToken();
        if (refresh.isBlank())
            return false
        try {
            val newToken = authApi.getRefreshAuthToken(
                grantType = Constants.NETWORK_GRANT_TYPE_REFRESH_TOKEN,
                clientId = Constants.NETWORK_CLIENT_ID,
                clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                refreshToken = refresh)
            saveTokensModel(accessToken = newToken.access_token, refreshToken = newToken.refresh_token)
            return true
        }catch (e: IOException){
            Log.d(Constants.TAG,"Ошибка обновления токена")
            return false
        }
    }

    override suspend fun authenticate(
        phone: String,
        password: String
    ): Boolean {
        try {
            val token =authApi.getAuthToken(
                grantType = Constants.NETWORK_GRANT_TYPE_GET_TOKEN,
                clientId = Constants.NETWORK_CLIENT_ID,
                clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                username =    phone,
                password = password,
                scope =  Constants.NETWORK_SCOPE
            )
            saveTokensModel(token.access_token,token.refresh_token)
            return true;
        }catch (io: IOException){
            Log.d(Constants.TAG,"Ошибка входа")
            return false
        }
    }

    override suspend fun saveTokensModel(accessToken: String, refreshToken: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun clearTokens() {
        saveTokensModel("","")
    }
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}