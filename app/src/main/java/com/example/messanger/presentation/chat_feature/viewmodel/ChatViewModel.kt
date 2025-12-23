package com.example.messanger.presentation.chat_feature.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.domain.model.MessageType
import com.example.messanger.domain.model.SendMessage
import com.example.messanger.domain.repository.ChatRepository
import com.example.messanger.domain.repository.MessageRepository
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.presentation.chat_feature.model.ChatVMState
import com.example.messanger.presentation.chat_feature.model.MessageUi
import com.example.messanger.presentation.naviagtion.routes.MainScreen
import com.example.messanger.util.Constants.TAG
import com.example.messanger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val chatsRepository: ChatRepository,
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository,
    private val chatsApi: ChatsApi
): ViewModel() {

    private val _state = MutableStateFlow(ChatVMState())
    val state = _state.asStateFlow()
    init {
        // Попробуем получить chatId из SavedStateHandle
        val initialChatId = savedStateHandle.get<String>(MainScreen.Chat.Args.CHAT_ID)
        Log.d(TAG,"Id чата равен $initialChatId")
        if (initialChatId != null) {
            _state.update {
                it.copy(chatId = initialChatId.toInt())
            }
            loadChat()
        }
    }

    private fun loadChat(){
        viewModelScope.launch {
            chatsRepository.getChatByIdWithLastMessages(_state.value.chatId).collect {
                result->
                when(result){
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false, errorMessage = "Всё накрылось медным тазом ${result.message}")
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success-> {
                        getMetaUsers(result.data!!.usersId)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                chatName = it.chatName,
                                messageUis = result.data!!.messages.map {
                                    MessageUi(
                                        id = it.id.toString(),
                                        text = it.message,
                                        senderName = _state.value.chatUsers.get(it.userId)?:"<Unknown>",
                                        senderId = it.userId.toString(),
                                        timestamp = System.currentTimeMillis() - 7200000,
                                        isMine = it.userId == _state.value.currentUserId
                                    )
                                }
                            )
                        }
                    }
                }
            }
            Log.d(TAG,_state.value.toString())
        }
    }
    private suspend fun getMetaUsers(userIds:List<Int>){
        val currentUser = userRepository.getCurrentUser()
        currentUser.collect { res->
            when(res){
                is Resource.Error<*> -> TODO()
                is Resource.Loading<*> -> TODO()
                is Resource.Success<*> -> {
                    _state.update {
                        it.copy(
                            currentUserId = res.data!!.id
                        )
                    }
                }
            }
        }

        var mapUsers = mutableMapOf<Int,String>()
       userIds.onEach{
           userRepository.getUserById(it).collect {
               result->
               when(result){
                   is Resource.Error<*> -> {}
                   is Resource.Loading<*> -> {}
                   is Resource.Success<*> ->{
                       val data = result.data!!
                       mapUsers[data.id] = data.name
                   }
               }
           }
       }
        _state.update {
            it.copy(
                chatUsers = mapUsers
            )
        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            messageRepository.sendMessage(
                _state.value.chatId,
                makeMessageData()
            ).collect {
                result->
                when(result){
                    is Resource.Error<*> -> {

                    }
                    is Resource.Loading<*> -> {

                    }
                    is Resource.Success<*> -> {

                    }
                }
            }
        }
        _state.update {
            it.copy(
                currentUserInput = ""
            )
        }
    }
    private fun makeMessageData(): SendMessage{
        return SendMessage(
            filesId = emptyList(),
            message = _state.value.currentUserInput,
            type = MessageType.TEXT
        )
    }
    fun updateInput(newInput: String) {
        _state.update {
            it.copy(
                currentUserInput = newInput
            )
        }
    }

}