package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.create_chats_feature.model.UserForChatUi

@Composable
fun UserListItem(
    user: UserForChatUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = if (user.isSelected) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        } else {
            MaterialTheme.colorScheme.surface
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Аватар
            UserAvatar(
                avatarUrl = user.avatarUrl,
                initials = user.initials,
                size = 48.dp
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Имя
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            
            // Индикатор выбора
            if (user.isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Выбрано",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}