package com.example.messanger.data.network.dto.util

import com.google.gson.annotations.SerializedName

data class UploadAvatarResponse(
    @SerializedName("message") val message: String,
    @SerializedName("avatar_url") val avatarUrl: String?
)