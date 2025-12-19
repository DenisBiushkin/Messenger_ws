package com.example.messanger.presentation.search_users_feature.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.search_users_feature.components.UserListItem
import com.example.messanger.presentation.search_users_feature.model.UserSearchItemUi

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            UserListItem(
                user = UserSearchItemUi(
                    id = "1",
                    name = "Алексей Петров",
                    isOnline = true
                ),
                onChatClick = {}
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            UserListItem(
                user = UserSearchItemUi(
                    id = "2",
                    name = "Мария Иванова",
                    lastSeen = "2 минуты назад"
                ),
                onChatClick = {}
            )
        }
    }
}