package com.example.messanger.presentation.chat_feature.screens

import ChatScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.messanger.presentation.chat_feature.model.ChatUserUi
import com.example.messanger.presentation.chat_feature.viewmodel.ChatViewModel



@Composable
fun ChatScreenWrapper(
    navController: NavController,
    onBackClick:()->Unit = {},
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    ChatScreen(
        chatUserUi = ChatUserUi(state.chatId.toString(),state.chatName),
        uiState = state,
        onSendMessage = { text ->
            viewModel.sendMessage(text)
        },
        onInputChange = { newInput ->
            viewModel.updateInput(newInput)
        },
        onBackClick = onBackClick
    )
}

