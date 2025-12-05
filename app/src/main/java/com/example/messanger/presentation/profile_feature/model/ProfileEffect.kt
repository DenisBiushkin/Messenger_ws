package com.example.messanger.presentation.profile_feature.model

sealed class ProfileEffect {

    object Logout : ProfileEffect()

    object ErrorAlert : ProfileEffect()
}