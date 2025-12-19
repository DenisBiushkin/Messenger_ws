package com.example.messanger.presentation.search_users_feature.components
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.search_users_feature.model.UserSearchItemUi

@Composable
fun UsersList(
    users: List<UserSearchItemUi>,
    onChatClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 8.dp)
    ) {
        items(users, key = { it.id }) { user ->
            UserListItem(
                user = user,
                onChatClick = { onChatClick(user.id) },
                onItemClick = { /* можно добавить просмотр профиля */ }
            )
        }
    }
}