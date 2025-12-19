package com.example.messanger.presentation.create_chats_feature.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.create_chats_feature.model.CreateChatEffect
import com.example.messanger.presentation.create_chats_feature.viewmodel.CreateChatViewModel

@Composable
fun CreateChatScreenWrapper(
    modifier: Modifier = Modifier,
    onChatCreated: (chatId: String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CreateChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is CreateChatEffect.ChatCreated -> onChatCreated(effect.chatId)
                is CreateChatEffect.Error -> {
                    // Можно показать Snackbar
                    // snackbarHostState.showSnackbar(effect.message)
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
}