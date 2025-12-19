package com.example.messanger.presentation.search_users_feature.screens
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.messanger.presentation.search_users_feature.model.SearchUserVMState
import com.example.messanger.presentation.search_users_feature.model.UserSearchItemUi

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchUserScreenPreview() {
    MaterialTheme {
        SearchUserScreen(
            state = SearchUserVMState(
                users = listOf(
                    UserSearchItemUi(
                        id = "1",
                        name = "Алексей Петров",
                        isOnline = true
                    ),
                    UserSearchItemUi(
                        id = "2",
                        name = "Мария Иванова",
                        lastSeen = "2 минуты назад"
                    )
                ),
                filteredUsers = listOf(
                    UserSearchItemUi(
                        id = "1",
                        name = "Алексей Петров",
                        isOnline = true
                    )
                ),
                searchQuery = "Алексей"
            ),
            onSearchQueryChanged = {},
            onChatClick = {},
            onBackClick = {},
            onErrorShown = {}
        )
    }
}