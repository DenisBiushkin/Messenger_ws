package com.example.messanger.presentation.profile_feature.model

sealed class ProfileEffect {
    object Logout : ProfileEffect()
    data class Error(val message: String) : ProfileEffect()
}