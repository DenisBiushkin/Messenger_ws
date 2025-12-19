package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GroupChatToggle(
    isGroupChat: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isGroupChat) Icons.Default.AccountBox else Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = if (isGroupChat) "Групповой чат" else "Личный чат",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            
            Switch(
                checked = isGroupChat,
                onCheckedChange = { onToggle() }
            )
        }
    }
}