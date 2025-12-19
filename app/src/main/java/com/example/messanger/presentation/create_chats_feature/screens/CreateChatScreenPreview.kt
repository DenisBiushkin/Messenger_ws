package com.example.messanger.presentation.create_chats_feature.screens
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.messanger.presentation.create_chats_feature.model.CreateChatVMState
import com.example.messanger.presentation.create_chats_feature.model.UserForChatUi

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateChatScreenPreview() {
    MaterialTheme {
        CreateChatScreen(
            state = CreateChatVMState(
                selectedUsers = listOf(
                    UserForChatUi(id = "1", name = "Алексей Петров", isSelected = true),
                    UserForChatUi(id = "2", name = "Мария Иванова", isSelected = true)
                ),
                filteredUsers = listOf(
                    UserForChatUi(id = "1", name = "Алексей Петров", isSelected = true),
                    UserForChatUi(id = "2", name = "Мария Иванова", isSelected = true),
                    UserForChatUi(id = "3", name = "Иван Сидоров")
                ),
                isGroupChat = true,
                showNameInput = true,
                chatName = "Рабочая группа"
            ),
            onEvent = {},
            onBackClick = {}
        )
    }
}