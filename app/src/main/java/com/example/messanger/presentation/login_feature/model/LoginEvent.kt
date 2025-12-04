package com.example.messanger.presentation.login_feature.model

import com.example.messanger.presentation.register_feature.models.RegistrationEvent

sealed class LoginEvent {

    data class PhoneChanged(val phone: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object OnLogin: LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
}