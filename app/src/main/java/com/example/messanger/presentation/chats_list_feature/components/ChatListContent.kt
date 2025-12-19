package com.example.messanger.presentation.chats_list_feature.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chats_list_feature.components.ChatListItem
import com.example.messanger.presentation.chats_list_feature.model.ChatListItemUi
import com.example.messanger.presentation.chats_list_feature.model.ChatListScreenState
import com.example.messanger.presentation.chats_list_feature.screens.ListChatScreen
import com.example.messanger.util.ChatThemes
@Composable
fun ChatListContent(
    state: ChatListScreenState,
    onItemClick: (ChatListItemUi) -> Unit,
    onRefresh: () -> Unit,
    onCreateChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            LoadingState(modifier = modifier.fillMaxSize())
        }
        
        state.error != null -> {
            ErrorState(
                error = state.error,
                onRetry = onRefresh,
                modifier = modifier.fillMaxSize()
            )
        }
        
        state.isEmpty -> {
            EmptyChatsState(
                onCreateChat = onCreateChat,
                modifier = modifier.fillMaxSize()
            )
        }
        
        else -> {
            ListChatScreen(
                chats = state.chats,
                onItemClick = onItemClick,
                modifier = modifier.fillMaxSize(),
                onCreateChat = onCreateChat
            )
        }
    }
}