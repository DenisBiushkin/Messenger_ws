package com.example.messanger.presentation.chat_feature.screens

import ChatScreen
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.messanger.presentation.chat_feature.model.ChatUserUi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.MoreVert

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chat_feature.model.ChatVMState
import com.example.messanger.presentation.chat_feature.model.MessageUi

@Preview(showBackground = true, showSystemUi = true, name = "Chat - Long Messages")
@Composable
fun ChatScreenLongMessagesPreview() {
    val previewChatUser = ChatUserUi(
        id = "user2",
        name = "Александр"
    )
    
    MaterialTheme {
        ChatScreen(
            onBackClick = {},
            chatUserUi = previewChatUser,
            uiState = ChatVMState(
                messageUis = listOf(
                    MessageUi(
                        id = "1",
                        text = "Это очень длинное сообщение от собеседника, которое должно занимать несколько строк и показывать, как интерфейс адаптируется под разный контент. Это важно для тестирования UI.",
                        senderName = "Александр",
                        senderId = "user2",
                        timestamp = System.currentTimeMillis() - 7200000,
                        isMine = false
                    ),
                    MessageUi(
                        id = "2",
                        text = "Понимаю! Это действительно длинное сообщение, но я его прочитал. Спасибо за разъяснение!",
                        senderName = "Вы",
                        senderId = "user1",
                        timestamp = System.currentTimeMillis() - 3600000,
                        isMine = true
                    )
                ),
                isLoading = false,
                currentUserInput = "Я тоже могу писать длинные сообщения для тестирования интерфейса чата."
            ),
            onSendMessage = { },
            onInputChange = { }
        )
    }
}