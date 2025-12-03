package com.example.messanger.presentation.register_feature.models

sealed class RegistrationEvent {
    data class NameChanged(val name: String) : RegistrationEvent()
    data class PhoneChanged(val phone: String) : RegistrationEvent()
    data class PasswordChanged(val password: String) : RegistrationEvent()
    data class PasswordConfirmationChanged(val passwordConfirmation: String) : RegistrationEvent()
    object TogglePasswordVisibility : RegistrationEvent()
    object ToggleConfirmPasswordVisibility : RegistrationEvent()
    object Register : RegistrationEvent()
    object ClearError : RegistrationEvent()
}