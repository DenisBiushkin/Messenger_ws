package com.example.messanger.presentation.search_users_feature.model

data class UsersScreenState(
    val users: List<UserItemUi> = emptyList(),
    val filteredUsers: List<UserItemUi> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null
)