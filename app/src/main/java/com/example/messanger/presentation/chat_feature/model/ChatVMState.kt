package com.example.messanger.presentation.chat_feature.model

data class ChatVMState(
    val chatId:Int = 0,
    val chatName: String  ="",
    val chatUsers : Map<Int,String> = emptyMap<Int,String>(),
    val currentUserId : Int = 0,
    val messageUis: List<MessageUi> = emptyList(),
    val isLoading: Boolean = false,
    val currentUserInput: String = "",
    val errorMessage: String? = null
)