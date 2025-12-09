package com.example.messanger.presentation.chats_list_feature.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.chats_list_feature.viemodel.ChatListViewModel


@Composable
fun ChatsListWrapperScreen(
    viewModel : ChatListViewModel = hiltViewModel()
){
    ChatScreen()
}