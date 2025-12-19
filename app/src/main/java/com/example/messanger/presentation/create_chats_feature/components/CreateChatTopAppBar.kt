package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateChatTopAppBar(
    selectedCount: Int,
    isGroupChat: Boolean,
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = if (isGroupChat) "Новая группа" else "Новый чат",
                    style = MaterialTheme.typography.titleLarge
                )
                if (selectedCount > 0) {
                    Text(
                        text = "Выбрано: $selectedCount",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
            }
        },
        actions = {
            IconButton(
                onClick = onCreateClick,
                enabled = selectedCount > 0
            ) {
                Icon(Icons.Default.Check, contentDescription = "Создать")
            }
        },
        modifier = modifier
    )
}