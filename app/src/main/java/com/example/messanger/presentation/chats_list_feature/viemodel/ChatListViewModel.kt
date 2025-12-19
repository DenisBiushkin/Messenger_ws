package com.example.messanger.presentation.chats_list_feature.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.network.dto.message.ChangeMessageRequest
import com.example.messanger.data.network.dto.message.SendMessageRequest
import com.example.messanger.presentation.chats_list_feature.model.ChatListItemUi
import com.example.messanger.presentation.chats_list_feature.model.ChatListScreenState
import com.example.messanger.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatsApi: ChatsApi,
    private val usersApi: UserApi,
    private val messageApi: MessageApi
): ViewModel(){

    private val _state = MutableStateFlow(ChatListScreenState(isLoading = true))
    val state: StateFlow<ChatListScreenState> = _state.asStateFlow()

    init {
        loadChats()
    }

    fun loadChats() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                // Имитация загрузки данных
                delay(1500)

                // Здесь будет реальная загрузка из репозитория
                // val chats = chatRepository.getChats()

                val mockChats = generateMockChats()
                val isEmpty = mockChats.isEmpty()

                _state.value = _state.value.copy(
                    chats = mockChats,
                    isLoading = false,
                    isEmpty = isEmpty
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Ошибка загрузки чатов: ${e.message}"
                )
            }
        }
    }

    fun refresh() {
        loadChats()
    }

    private fun generateMockChats(): List<ChatListItemUi> {
        // Для тестирования можно вернуть пустой список
        // return emptyList()

        return listOf(
            ChatListItemUi(
                id = "chat_1",
                userName = "Алексей Петров",
                lastMessage = "Привет! Как дела?",
                unreadCount = 2,
                timestamp = "10:30",
                isOnline = true
            ),
            ChatListItemUi(
                id = "chat_2",
                userName = "Мария Иванова",
                lastMessage = "Давай встретимся завтра",
                timestamp = "Вчера",
                isOnline = false
            ),
            ChatListItemUi(
                id = "chat_3",
                userName = "Рабочая группа",
                lastMessage = "Иван: Отправил файлы на проверку",
                unreadCount = 5,
                timestamp = "15:45",
                isGroup = true,
                participantCount = 8
            ),
            ChatListItemUi(
                id = "chat_4",
                userName = "Иван Сидоров",
                lastMessage = "Спасибо за помощь!",
                timestamp = "12.01",
                isOnline = true
            ),
            ChatListItemUi(
                id = "chat_5",
                userName = "Екатерина Волкова",
                lastMessage = "Хорошего дня!",
                timestamp = "11.01",
                isOnline = false
            )
        )
    }
}