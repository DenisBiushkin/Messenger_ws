package com.example.messanger.presentation.register_feature.models

sealed class RegistrationEffect {
    object NavigateToHome : RegistrationEffect()
    object NavigateToLogin : RegistrationEffect()
    data class ShowSnackbar(val message: String) : RegistrationEffect()
}