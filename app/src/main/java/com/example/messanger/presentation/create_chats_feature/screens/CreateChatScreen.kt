package com.example.messanger.presentation.create_chats_feature.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.create_chats_feature.components.CreateChatTopAppBar
import com.example.messanger.presentation.create_chats_feature.components.GroupChatToggle
import com.example.messanger.presentation.create_chats_feature.components.GroupNameInput
import com.example.messanger.presentation.create_chats_feature.components.SearchBar
import com.example.messanger.presentation.create_chats_feature.components.SelectedUsersSection
import com.example.messanger.presentation.create_chats_feature.components.UsersListContent
import com.example.messanger.presentation.create_chats_feature.model.CreateChatEvent
import com.example.messanger.presentation.create_chats_feature.model.CreateChatVMState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateChatScreen(
    state: CreateChatVMState,
    onEvent: (CreateChatEvent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CreateChatTopAppBar(
                selectedCount = state.selectedUsers.size,
                isGroupChat = state.isGroupChat,
                onBackClick = onBackClick,
                onCreateClick = { onEvent(CreateChatEvent.CreateChat) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Выбранные пользователи
            if (state.selectedUsers.isNotEmpty()) {
                SelectedUsersSection(
                    selectedUsers = state.selectedUsers,
                    onUserRemoved = { userId ->
                        onEvent(CreateChatEvent.UserSelected(userId))
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            // Переключатель группового чата
            if (state.selectedUsers.size > 1) {
                GroupChatToggle(
                    isGroupChat = state.isGroupChat,
                    onToggle = { onEvent(CreateChatEvent.ToggleGroupChat) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            
            // Поле для имени группы
            if (state.showNameInput) {
                GroupNameInput(
                    value = state.chatName,
                    onValueChange = { onEvent(CreateChatEvent.ChatNameChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            
            // Поиск
            SearchBar(
                query = state.searchQuery,
                onQueryChange = { onEvent(CreateChatEvent.SearchQueryChanged(it)) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            // Список пользователей
            UsersListContent(
                state = state,
                onUserClick = { userId -> onEvent(CreateChatEvent.UserSelected(userId)) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}