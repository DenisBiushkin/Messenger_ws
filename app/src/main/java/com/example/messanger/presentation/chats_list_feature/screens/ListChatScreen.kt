package com.example.messanger.presentation.chats_list_feature.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chats_list_feature.components.ChatListItem
import com.example.messanger.presentation.chats_list_feature.model.ChatListItemUi
import com.example.messanger.util.ChatThemes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListChatScreen(
    modifier: Modifier = Modifier,
    chats: List< ChatListItemUi> = emptyList< ChatListItemUi>(),
    onItemClick:(ChatListItemUi)->Unit,
    onCreateChat:()->Unit = {},
    theme: ChatThemes = ChatThemes
) {

    Scaffold(
       // modifier =modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Messanger Ws") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = theme.PurpleTheme.secondaryColor, // Цвет фона
                    titleContentColor = Color.Black, // Цвет текста заголовка
                    actionIconContentColor = Color.Black, // Цвет иконок действий
                    navigationIconContentColor = Color.Black // Цвет иконки навигации
                ),
                actions = {
                    IconButton(
                        onClick =onCreateChat
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Создать новый чат")
                    }

                }
            )
        }
    ) {
        paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(

               // contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(chats) { chat ->
                    ChatListItem(
                        chatItem = chat,
                        onClick ={
                            onItemClick(chat)
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

}