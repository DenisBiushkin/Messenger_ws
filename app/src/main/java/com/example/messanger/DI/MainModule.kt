package com.example.messanger.DI

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.source.AppDatabase
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.data.token.TokenProviderImpl
import com.example.messanger.domain.validation.AuthValidator
import com.example.messanger.domain.validation.AuthValidatorImpl
import com.example.messanger.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Application): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.ROOM_DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideTokenProvider(
        dataStore:DataStore<Preferences>,
        authApi: AuthApi
    ): TokenProvider{
        return TokenProviderImpl(authApi,dataStore)
    }

    @Singleton
    @Provides
    fun provideAuthValidator(): AuthValidator = AuthValidatorImpl()



}