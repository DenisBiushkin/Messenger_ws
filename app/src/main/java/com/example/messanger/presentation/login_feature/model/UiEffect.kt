package com.example.messanger.presentation.login_feature.model

sealed class UiEffect {
        object NavigateToHome : UiEffect()
        object NavigateToRegistration : UiEffect()
        object NavigateToForgotPassword : UiEffect()
        data class ShowError(val message: String) : UiEffect()
    }