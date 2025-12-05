package com.example.messanger.data.network

import com.example.messanger.data.network.dto.RegisterRequest
import com.example.messanger.data.network.dto.RegisterResponse
import com.example.messanger.data.network.dto.UploadAvatarResponse
import com.example.messanger.data.network.dto.UserDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MainApi {

    @GET("api/user/profile")
    suspend fun getUser(

    ): UserDto

    @Multipart
    @POST("user/avatar")
    suspend fun uploadAvatar(
        @Part avatar: MultipartBody.Part
    ): Response<UploadAvatarResponse>


    @POST("api/register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): RegisterResponse


}