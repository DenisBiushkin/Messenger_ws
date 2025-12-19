package com.example.messanger.presentation.search_users_feature.components
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.messanger.presentation.search_users_feature.model.SearchUserVMState

@Composable
fun SearchUserContent(
    state: SearchUserVMState,
    onChatClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when {
            // Состояние загрузки
            state.isLoading -> {
                LoadingState(
                    isSearching = state.searchQuery.isNotEmpty()
                )
            }
            
            // Пустой список пользователей (без поиска)
            state.isEmpty -> {
                EmptyUsersList()
            }
            
            // Пустой результат поиска
            state.isEmptySearchResult -> {
                EmptySearchResult(
                    query = state.searchQuery
                )
            }
            
            // Список пользователей
            else -> {
                UsersList(
                    users = state.filteredUsers,
                    onChatClick = onChatClick
                )
            }
        }
    }
}