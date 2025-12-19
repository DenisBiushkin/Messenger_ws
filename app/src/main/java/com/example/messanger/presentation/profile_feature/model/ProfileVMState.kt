package com.example.messanger.presentation.profile_feature.model

import com.example.messanger.data.network.dto.user.UserDto


data class ProfileVMState(
    val user: UserDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)