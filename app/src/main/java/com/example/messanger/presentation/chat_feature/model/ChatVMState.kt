package com.example.messanger.presentation.chat_feature.model

data class ChatVMState(
    val messageUis: List<MessageUi> = emptyList(),
    val isLoading: Boolean = false,
    val currentUserInput: String = "",
    val errorMessage: String? = null
)