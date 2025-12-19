package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.create_chats_feature.model.CreateChatVMState

@Composable
fun UsersListContent(
    state: CreateChatVMState,
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        
        state.filteredUsers.isEmpty() -> {
            EmptyUsersList(
                searchQuery = state.searchQuery,
                modifier = modifier
            )
        }
        
        else -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(state.filteredUsers, key = { it.id }) { user ->
                    UserListItem(
                        user = user,
                        onClick = { onUserClick(user.id) }
                    )
                }
            }
        }
    }
}