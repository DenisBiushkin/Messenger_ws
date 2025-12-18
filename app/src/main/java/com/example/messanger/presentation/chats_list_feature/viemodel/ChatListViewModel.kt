package com.example.messanger.presentation.chats_list_feature.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.network.dto.chat.CreateChatRequest
import com.example.messanger.data.network.dto.message.SendMessageRequest
import com.example.messanger.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatsApi: ChatsApi,
    private val usersApi: UserApi,
    private val messageApi: MessageApi
): ViewModel(){

    init{
        viewModelScope.launch {

           // val userDto = usersApi.getUsers(userName = "antonizsar1356")
           // val userDto = chatsApi.getUsersInChatById(31)
           // Log.d(TAG,userDto.toString())

//            chatsApi.createChat(CreateChatRequest(
//                title = "Тест создания",
//                type = "private",
//                users = listOf(1,2,3,4,5)
//            ))
//            messageApi.sendMessage(
//                32,
//                SendMessageRequest(
//                    files = emptyList(),
//                    "Ntcn jnghfdrb cjj,ottyb",
//                    type = "text"
//                )
//            )
               val message = messageApi.getMessageByChatId(32)
              Log.d(TAG,message.toString())
            getAllChats()
        }
    }

    suspend fun getAllChats(){
              val chatsDto = chatsApi.getChats()
         Log.d(TAG,chatsDto.data.toString())
    }
}