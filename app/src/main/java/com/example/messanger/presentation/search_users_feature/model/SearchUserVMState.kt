package com.example.messanger.presentation.search_users_feature.model

data class SearchUserVMState(
    val users: List<UserSearchItemUi> = emptyList(),
    val filteredUsers: List<UserSearchItemUi> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null
) {
    // Вычисляемое свойство для empty state
    val isEmpty: Boolean get() = 
        !isLoading && searchQuery.isEmpty() && users.isEmpty()
    
    // Вычисляемое свойство для empty search result
    val isEmptySearchResult: Boolean get() = 
        !isLoading && searchQuery.isNotEmpty() && filteredUsers.isEmpty()
}