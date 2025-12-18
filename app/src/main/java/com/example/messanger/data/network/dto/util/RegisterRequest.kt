package com.example.messanger.data.network.dto.util

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("password_confirmation")
    val passwordConfirmation: String
)