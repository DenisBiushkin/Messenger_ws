package com.example.messanger.presentation.create_chats_feature.model

sealed class CreateChatEffect {
    data class ChatCreated(val chatId: String) : CreateChatEffect()
    data class Error(val message: String) : CreateChatEffect()
}