package com.example.messanger.presentation.chat_feature.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.messanger.presentation.chat_feature.model.ChatVMState
import com.example.messanger.presentation.naviagtion.routes.MainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _state = MutableStateFlow(ChatVMState())
    val state = _state.asStateFlow()
    init {
        // Попробуем получить chatId из SavedStateHandle
        val initialChatId = savedStateHandle.get<String>(MainScreen.Chat.Args.CHAT_ID)
        if (initialChatId != null) {
          //  loadChat(initialChatId)
        }
    }

    fun sendMessage(text: String) {}
    fun updateInput(newInput: String) {}

}