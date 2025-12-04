package com.example.messanger.presentation.login_feature.model



data class LoginVMState(
    val phone:String = "",
    val password:String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
