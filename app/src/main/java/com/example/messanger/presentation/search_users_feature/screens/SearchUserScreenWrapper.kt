package com.example.messanger.presentation.search_users_feature.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.search_users_feature.SearchUserViewModel

@Composable
fun SearchUserScreenWrapper(
    modifier: Modifier = Modifier,
    onChatClick: (userId: String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: SearchUserViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    SearchUserScreen(
        modifier =modifier,
        state = state,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onChatClick = { userId ->
            onChatClick(userId)
            viewModel.onUserClick(userId)
        },
        onBackClick = onBackClick,
        onErrorShown = viewModel::clearError
    )
}