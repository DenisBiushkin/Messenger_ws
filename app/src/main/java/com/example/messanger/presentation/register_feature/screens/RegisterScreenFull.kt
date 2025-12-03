package com.example.messanger.presentation.register_feature.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.messanger.presentation.register_feature.components.RegistrationScreen
import com.example.messanger.presentation.register_feature.models.RegistrationEvent
import com.example.messanger.presentation.register_feature.viemodels.RegisterViewModel

@Composable
fun RegisterScreenFull(
    modifier: Modifier = Modifier,
    navController: NavController,
    onRegistrationSuccess: ()->Unit,
    onNavigateToLogin:()->Unit,

    viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
){
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        snapshotFlow { state.errorMessage }
            .collect { error ->
                if (error != null) {
                    // Можно показать Snackbar или другую обработку ошибок
                }
            }
    }

    RegistrationScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                is RegistrationEvent.NameChanged -> viewModel.updateName(event.name)
                is RegistrationEvent.PhoneChanged -> viewModel.updatePhone(event.phone)
                is RegistrationEvent.PasswordChanged -> viewModel.updatePassword(event.password)
                is RegistrationEvent.PasswordConfirmationChanged ->
                    viewModel.updatePasswordConfirmation(event.passwordConfirmation)
                RegistrationEvent.TogglePasswordVisibility -> viewModel.togglePasswordVisibility()
                RegistrationEvent.ToggleConfirmPasswordVisibility ->
                    viewModel.toggleConfirmPasswordVisibility()
                RegistrationEvent.Register -> viewModel.register(
                    onSuccess = onRegistrationSuccess,
                    onError = { error ->
                        // Ошибка уже обрабатывается в ViewModel
                    }
                )
                RegistrationEvent.ClearError -> viewModel.clearError()
            }
        },
        onNavigateToLogin = onNavigateToLogin
    )
}