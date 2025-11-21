package com.example.messanger.data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[REFRESH_TOKEN] = refreshToken
        }
    }

    val tokens: Flow<AuthTokens> = dataStore.data
        .map { prefs ->
            AuthTokens(
                accessToken = prefs[ACCESS_TOKEN].orEmpty(),
                refreshToken = prefs[REFRESH_TOKEN].orEmpty()
            )
        }
        .distinctUntilChanged()


    suspend fun getTokens(): AuthTokens {
        val prefs = dataStore.data.first()
        return AuthTokens(
            accessToken = prefs[ACCESS_TOKEN].orEmpty(),
            refreshToken = prefs[REFRESH_TOKEN].orEmpty()
        )
    }
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}
