package com.example.messanger.presentation.create_chats_feature.model

data class CreateChatVMState(
    val users: List<UserForChatUi> = emptyList(),
    val filteredUsers: List<UserForChatUi> = emptyList(),
    val selectedUsers: List<UserForChatUi> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val chatName: String = "",
    val isGroupChat: Boolean = false,
    val error: String? = null,
    val showNameInput: Boolean = false // Показывать поле для имени группы
)