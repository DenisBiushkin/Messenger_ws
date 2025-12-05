package com.example.messanger.presentation.login_feature.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.login_feature.model.LoginEvent
import com.example.messanger.presentation.login_feature.model.LoginVMState
import com.example.messanger.presentation.login_feature.model.UiEffect
import com.example.messanger.presentation.login_feature.viemodels.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreenWrapper(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Обработка UI эффектов
    LaunchedEffect(Unit) {
        viewModel.uiEffects.collect { effect ->
            when (effect) {
                is UiEffect.NavigateToHome -> onLoginSuccess()
                is UiEffect.NavigateToRegistration -> onNavigateToRegistration()
                is UiEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()

                is UiEffect.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0xFFF6F0FF),
                contentColor = Color.Red           // ⚪ Белый текст
            )
        } }
    ) { padding ->

        Box(Modifier.padding(padding)) {

            // --- ваш основной UI формы здесь ---
            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                state = state.value,
                onEvent = viewModel::onEvent,
                onLoginSuccess = onLoginSuccess,
                onNavigateToRegistration = onNavigateToRegistration,
                onNavigateToForgotPassword = onNavigateToForgotPassword
            )

            //АНИМИРОВАННАЯ ЗАГРУЗКА
            AnimatedVisibility(
                visible = state.value.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}


