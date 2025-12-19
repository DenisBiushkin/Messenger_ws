package com.example.messanger.presentation.create_chats_feature.model

sealed class CreateChatEvent {
    data class SearchQueryChanged(val query: String) : CreateChatEvent()
    data class UserSelected(val userId: String) : CreateChatEvent()
    data class ChatNameChanged(val name: String) : CreateChatEvent()
    object ToggleGroupChat : CreateChatEvent()
    object CreateChat : CreateChatEvent()
    object LoadUsers : CreateChatEvent()
}