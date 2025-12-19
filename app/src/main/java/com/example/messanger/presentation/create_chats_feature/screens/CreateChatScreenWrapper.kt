package com.example.messanger.presentation.create_chats_feature.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.create_chats_feature.model.CreateChatEffect
import com.example.messanger.presentation.create_chats_feature.viewmodel.CreateChatViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateChatScreenWrapper(
    modifier: Modifier = Modifier,
    onChatCreated: (chatId: String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CreateChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is CreateChatEffect.ChatCreated -> onChatCreated(effect.chatId)
                is CreateChatEffect.Error -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                            actionLabel = "OK",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }
    
    CreateChatScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick
    )
    androidx.compose.material3.Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        CreateChatScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onBackClick = onBackClick,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        )
    }
}
