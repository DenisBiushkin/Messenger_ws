package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.create_chats_feature.model.UserForChatUi

@Composable
fun SelectedUserChip(
    user: UserForChatUi,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        tonalElevation = 1.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            // Аватар
            UserAvatar(
                avatarUrl = user.avatarUrl,
                initials = user.initials,
                size = 32.dp
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // Имя
            Text(
                text = user.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            // Кнопка удаления
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Удалить",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
