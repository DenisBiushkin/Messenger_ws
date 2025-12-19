package com.example.messanger.presentation.search_users_feature.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.messanger.presentation.search_users_feature.components.SearchBar
import com.example.messanger.presentation.search_users_feature.components.SearchUserContent
import com.example.messanger.presentation.search_users_feature.components.SearchUserTopAppBar
import com.example.messanger.presentation.search_users_feature.model.SearchUserVMState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUserScreen(
    state: SearchUserVMState,
    onSearchQueryChanged: (String) -> Unit,
    onChatClick: (userId: String) -> Unit,
    onBackClick: () -> Unit,
    onErrorShown: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Обработка ошибок через Snackbar
    LaunchedEffect(state.error) {
        if (state.error != null) {
            val result = snackbarHostState.showSnackbar(
                message = state.error,
                actionLabel = "OK",
                duration = SnackbarDuration.Long
            )
            if (result == SnackbarResult.Dismissed) {
                onErrorShown()
            }
        }
    }

    Scaffold(
        topBar = {
            SearchUserTopAppBar(
                onBackClick = onBackClick
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            // Поле поиска
            SearchBar(
                query = state.searchQuery,
                onQueryChange = onSearchQueryChanged,
                onSearch = { /* уже работает через debounce */ }
            )

            // Содержимое экрана
            SearchUserContent(
                state = state,
                onChatClick = onChatClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
