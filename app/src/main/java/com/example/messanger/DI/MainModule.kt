package com.example.messanger.DI

import android.app.Application
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.MainApi
import com.example.messanger.data.source.AppDatabase
import com.example.messanger.util.Constants
import com.example.messanger.util.Constants.NETWORK_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(NETWORK_API_BASE_URL)
//            //.client(okHttpClient)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthApi(retrofit: Retrofit): AuthApi {
//        return retrofit.create(AuthApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMainApi(retrofit: Retrofit): MainApi {
//        return retrofit.create(MainApi::class.java)
//    }
}