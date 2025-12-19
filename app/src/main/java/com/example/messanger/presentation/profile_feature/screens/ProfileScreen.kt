package com.example.messanger.presentation.profile_feature.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.messanger.presentation.profile_feature.components.ErrorState
import com.example.messanger.presentation.profile_feature.components.ProfileContent
import com.example.messanger.presentation.profile_feature.model.ProfileVMState
import com.example.messanger.presentation.search_users_feature.components.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileVMState,
    onLogout: () -> Unit,
    onReload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },

    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    LoadingState(true)
                }

                state.error != null -> {
                    ErrorState(
                        error = state.error,
                        onRetry = onReload
                    )
                }

                state.user != null -> {
                    ProfileContent(
                        user = state.user,
                        onLogout = onLogout
                    )
                }
            }
        }
    }
}
