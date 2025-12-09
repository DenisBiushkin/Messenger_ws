package com.example.messanger.presentation.chats_list_feature.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chats_list_feature.components.ChatListItem
import com.example.messanger.presentation.chats_list_feature.model.ChatItemUi

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val chats = remember {
            listOf(
                ChatItemUi(
                    id = "1",
                    userName = "Анна Петрова",
                    lastMessage = "Привет! Как дела? Когда встретимся?",
                    unreadCount = 3,
                    timestamp = "10:30",
                    isOnline = true
                ),
                ChatItemUi(
                    id = "2",
                    userName = "Иван Сидоров",
                    lastMessage = "Отправил тебе документы по проекту",
                    unreadCount = 1,
                    timestamp = "Вчера"
                ),
                ChatItemUi(
                    id = "3",
                    userName = "Мария Иванова",
                    lastMessage = "Спасибо за помощь!",
                    timestamp = "15 апр"
                ),
                ChatItemUi(
                    id = "4",
                    userName = "Алексей Смирнов",
                    lastMessage = "Завтра в 14:00 на совещании",
                    timestamp = "14 апр"
                ),
                ChatItemUi(
                    id = "5",
                    userName = "Екатерина Волкова",
                    lastMessage = "👋",
                    isOnline = true,
                    timestamp = "12 апр"
                )
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(chats) { chat ->
                ChatListItem(
                    chatItem = chat,
                    onClick = {

                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(start = 88.dp, end = 16.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}