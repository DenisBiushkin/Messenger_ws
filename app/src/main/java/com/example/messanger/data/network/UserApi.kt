package com.example.messanger.data.network

import com.example.messanger.data.network.dto.util.RegisterRequest
import com.example.messanger.data.network.dto.util.RegisterResponse
import com.example.messanger.data.network.dto.util.UploadAvatarResponse
import com.example.messanger.data.network.dto.user.UsersDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("offset") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("search") userName: String? = null,
    ): UsersDto

    @Multipart
    @POST("users/avatar")
    suspend fun uploadAvatar(
        @Part avatar: MultipartBody.Part
    ): Response<UploadAvatarResponse>


    @POST("register")//ok
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): RegisterResponse


}