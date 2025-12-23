package com.example.messanger.presentation.chats_list_feature.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.network.dto.message.ChangeMessageRequest
import com.example.messanger.data.network.dto.message.SendMessageRequest
import com.example.messanger.domain.model.ChatType
import com.example.messanger.domain.repository.ChatRepository
import com.example.messanger.presentation.chats_list_feature.model.ChatListItemUi
import com.example.messanger.presentation.chats_list_feature.model.ChatListScreenState
import com.example.messanger.util.Constants.TAG
import com.example.messanger.util.Resource
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
    private val messageApi: MessageApi,
    private val chatRepository: ChatRepository
): ViewModel(){

    private val _state = MutableStateFlow(ChatListScreenState(isLoading = true))
    val state: StateFlow<ChatListScreenState> = _state.asStateFlow()

    init {
        loadChats()
        viewModelScope.launch {
            testLoadData()
        }
    }

    private suspend fun testLoadData(){
        viewModelScope.launch {
            chatRepository.createChat(
                title = "Api test",
                type = ChatType.GROUP,
                listOf(1,2,3,4)
            ).collect {
                when(it){
                    is Resource.Error ->{
                        Log.d(TAG,"Ошибка получения данных "+it.message)
                    }
                    is Resource.Success ->{
                        Log.d(TAG,it.data.toString())
                    }
                    is Resource.Loading ->{
                        Log.d(TAG,"Загрузка данных")
                    }
                }
            }

        }

    }

    fun loadChats() {
        viewModelScope.launch {

            chatRepository.getChats(0,20).collect { data ->
                when(data){
                    is Resource.Error ->{
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "Ошибка загрузки чатов: ${data.message}"
                        )
                    }
                    is Resource.Success ->{
                       // val mockChats = generateMockChats()

                        val mockChats = data.data?.map {
                            ChatListItemUi(
                                id = it.id.toString(),
                                userName = it.title,
                                lastMessage = "Пока пусто",
                                unreadCount = 0,
                                timestamp = "10:30",
                                isOnline = false
                            )
                        }?: emptyList()
                        val isEmpty = mockChats.isEmpty()

                        _state.value = _state.value.copy(
                            chats = mockChats,
                            isLoading = false,
                            isEmpty = isEmpty
                        )

                        Log.d(TAG,data.data.toString())
                    }
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(isLoading = true, error = null)
                    }
                }
            }
        }
    }

    fun refresh() {
        Log.d(TAG,"Идет повторное получение данных")
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