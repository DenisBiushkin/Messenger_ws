package com.example.messanger.presentation.register_feature.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.messanger.presentation.login_feature.model.UiEffect
import com.example.messanger.presentation.register_feature.components.RegistrationScreen
import com.example.messanger.presentation.register_feature.models.RegistrationEffect
import com.example.messanger.presentation.register_feature.models.RegistrationEvent
import com.example.messanger.presentation.register_feature.viemodels.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreenFull(
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigateToLogin:()->Unit,

    viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
){
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Обработка UI эффектов
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                RegistrationEffect.NavigateToHome -> {
                    onNavigateToLogin()
                }
                RegistrationEffect.NavigateToLogin -> {
                   onNavigateToLogin()
                }
                is RegistrationEffect.ShowSnackbar -> {
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
            RegistrationScreen(
                state = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                },
                onNavigateToLogin = onNavigateToLogin
            )
            AnimatedVisibility(
                visible = state.isLoading,
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
