package com.example.messanger.data.repository

import android.util.Log
import com.example.messanger.data.mapper.RegisterUserMapper
import com.example.messanger.data.mapper.UserMapper
import com.example.messanger.data.network.UserApi
import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.model.User
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.util.Constants
import com.example.messanger.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException


class UserRepositoryImpl(
    private val userApi: UserApi
): UserRepository {

    private val userMapper = UserMapper()

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

    override suspend fun getCurrentUser(): Flow<Resource<User>> = flow{
        try {
            val currentDto =userApi.getCurrentUser().data
            emit(Resource.Success(userMapper.fromFirstToSecond(currentDto)))
        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun getUserById(id: Int): Flow<Resource<User>> = flow{
        try {
            val currentDto =userApi.getUserById(id , id).data
            emit(Resource.Success(userMapper.fromFirstToSecond(currentDto)))
        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun getUsers(
        offset: Int,
        limit: Int
    ): Flow<Resource<List<User>>> = flow{
        try {
            val currentDto =userApi.getUsers(offset,limit).data
            emit(Resource.Success(currentDto.map {userMapper.fromFirstToSecond( it) }))
        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

    override suspend fun getUsersByName(
        username: String,
        offset: Int,
        limit: Int
    ): Flow<Resource<List<User>>> = flow{
        try {
            val currentDto =userApi.getUsers(offset,limit,username).data
            emit(Resource.Success(currentDto.map {userMapper.fromFirstToSecond( it) }))
        }catch (e: IOException){
            emit(Resource.Error(message = "Нет подключения к интернету"))
        }catch (e: Exception){
            emit( Resource.Error(message = "Иная ошибка"))
        }
    }

}