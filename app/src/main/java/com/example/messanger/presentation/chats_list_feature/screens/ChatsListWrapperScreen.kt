package com.example.messanger.presentation.chats_list_feature.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.chats_list_feature.components.ChatListContent
import com.example.messanger.presentation.chats_list_feature.viemodel.ChatListViewModel
import com.example.messanger.util.Constants.TAG


@Composable
fun ChatsListWrapperScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatListViewModel = hiltViewModel(),
    onChatClick: (String) -> Unit,
    onCreateChat: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    // Автообновление при возвращении на экран
    LaunchedEffect(Unit) {
        // Можно добавить логику автообновления
    }

    ChatListContent(
        state = state,
        onItemClick = { chatItemUi ->

            onChatClick(chatItemUi.id)
        },
        onCreateChat = onCreateChat,
        onRefresh = { viewModel.refresh() },
        modifier = modifier
    )
}