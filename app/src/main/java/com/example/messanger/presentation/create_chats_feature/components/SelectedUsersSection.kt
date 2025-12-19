package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.create_chats_feature.model.UserForChatUi

@Composable
fun SelectedUsersSection(
    selectedUsers: List<UserForChatUi>,
    onUserRemoved: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Выбраны:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(selectedUsers, key = { it.id }) { user ->
                SelectedUserChip(
                    user = user,
                    onRemove = { onUserRemoved(user.id) }
                )
            }
        }
    }
}
