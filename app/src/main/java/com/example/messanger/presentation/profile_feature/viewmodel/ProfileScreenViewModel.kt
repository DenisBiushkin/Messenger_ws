package com.example.messanger.presentation.profile_feature.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.domain.usecases.auth.LogoutUserUseCase
import com.example.messanger.presentation.profile_feature.model.ProfileEffect
import com.example.messanger.presentation.profile_feature.model.ProfileEvent
import com.example.messanger.presentation.profile_feature.model.ProfileVMState
import com.example.messanger.presentation.register_feature.models.RegisterVMState
import com.example.messanger.presentation.register_feature.models.RegistrationEffect
import com.example.messanger.util.Constants
import com.example.messanger.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val userApi: UserApi,
    private val messageApi: MessageApi,
    private val chatsApi: ChatsApi
) : ViewModel(){

    private val _state = MutableStateFlow(ProfileVMState())
    val state: StateFlow<ProfileVMState> = _state.asStateFlow()

    private val _effects = Channel<ProfileEffect>()
    val effects = _effects.receiveAsFlow()
    init{
        viewModelScope.launch {
            val token =tokenProvider.getAccessToken()
            Log.d(TAG, "Токен в приложении "+token)


//            val data = userApi.getUser(1)
//            val data2 = messageApi.getMessageById(30)
//            Log.d(TAG,data.toString())
//            Log.d(TAG,data2.data.toString())


        }
    }

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.Logout -> {
                _state.update { ProfileVMState() }
                viewModelScope.launch {
                    logoutUserUseCase.execute()
                    delay(100)
                    _effects.send(ProfileEffect.Logout)
                }
            }
        }
    }
}