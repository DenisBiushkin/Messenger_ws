package com.example.messanger.presentation.chat_feature.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chat_feature.model.ChatTheme
import com.example.messanger.presentation.chat_feature.model.MessageUi
import com.example.messanger.util.ChatThemes

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun MessagesList(
    messages: List<MessageUi>,
    currentUserId: String,
    theme: ChatTheme = ChatThemes.PurpleTheme,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(messages) { message ->
            MessageItem(
                messageUi = message,
                currentUserId = currentUserId,
                theme = theme
            )
        }
    }
}