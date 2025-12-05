package com.example.messanger.presentation.profile_feature.model

sealed class ProfileEvent {

    object Logout: ProfileEvent()
}