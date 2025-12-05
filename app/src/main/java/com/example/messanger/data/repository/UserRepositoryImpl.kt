package com.example.messanger.data.repository

import android.util.Log
import com.example.messanger.data.mapper.RegisterUserMapper
import com.example.messanger.data.network.UserApi
import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.util.Constants
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import okio.IOException


class UserRepositoryImpl(
    private val userApi: UserApi
): UserRepository {
    override suspend fun registerUser(registerUser: RegisterUser): Resource<Unit> {
        try {
            Log.d(Constants.TAG,RegisterUserMapper().fromFirstToSecond(registerUser).toString())
            userApi.registerUser(RegisterUserMapper().fromFirstToSecond(registerUser))
            return Resource.Success(Unit)
        }catch (e: IOException){
            return Resource.Error(message = "Нет подключения к интернету")
        }catch (e: Exception){
            return Resource.Error(message = "Иная ошибка")
        }
    }
}