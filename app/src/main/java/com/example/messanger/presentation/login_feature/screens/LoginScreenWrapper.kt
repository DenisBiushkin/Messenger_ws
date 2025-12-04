package com.example.messanger.presentation.login_feature.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.login_feature.viemodels.LoginViewModel

@Composable
fun LoginScreenWrapper(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
){
    val state = viewModel.state.collectAsState()

    LoginScreen(
        state = state.value,
        onEvent = viewModel::onEvent,
        onLoginSuccess = onLoginSuccess, // можно передавать через events
        onNavigateToRegistration = onNavigateToRegistration,
        onNavigateToForgotPassword = onNavigateToForgotPassword
    )

}