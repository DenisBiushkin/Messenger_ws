package com.example.messanger.presentation.chats_list_feature.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.chats_list_feature.model.ChatItemUi
import com.example.messanger.presentation.chats_list_feature.viemodel.ChatListViewModel


@Composable
fun ChatsListWrapperScreen(
    modifier: Modifier = Modifier,
    viewModel : ChatListViewModel = hiltViewModel(),
    onChatClick:(String)->Unit
){
    ListChatScreen(
        modifier = modifier,
        onItemClick ={
            chatItemUi->
            onChatClick(chatItemUi.id)
        }
    )
}