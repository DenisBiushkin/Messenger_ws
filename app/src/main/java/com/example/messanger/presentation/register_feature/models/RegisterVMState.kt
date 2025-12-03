package com.example.messanger.presentation.register_feature.models

data class RegisterVMState(
    val name: String = "",
    val phone: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)