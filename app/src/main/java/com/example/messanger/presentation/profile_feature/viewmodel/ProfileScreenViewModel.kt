package com.example.messanger.presentation.profile_feature.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.ChatsApi
import com.example.messanger.data.network.MessageApi
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.network.dto.user.AvatarDto
import com.example.messanger.data.network.dto.user.UserDto
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.domain.repository.UserRepository
import com.example.messanger.domain.usecases.auth.LogoutUserUseCase
import com.example.messanger.presentation.profile_feature.model.ProfileEffect
import com.example.messanger.presentation.profile_feature.model.ProfileEvent
import com.example.messanger.presentation.profile_feature.model.ProfileVMState
import com.example.messanger.util.Constants.TAG
import com.example.messanger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val userRepository: UserRepository,
    private val userApi: UserApi,
    private val messageApi: MessageApi,
    private val chatsApi: ChatsApi
) : ViewModel(){

    private val _state = MutableStateFlow(ProfileVMState())
    val state: StateFlow<ProfileVMState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<ProfileEffect>()
    val effects: SharedFlow<ProfileEffect> = _effects.asSharedFlow()


    init{
        viewModelScope.launch {
            val token =tokenProvider.getAccessToken()
            Log.d(TAG, "Токен в приложении "+token)
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LoadProfile -> loadProfile()
            ProfileEvent.Logout -> logout()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {

            val user = userRepository.getCurrentUser().collect {
                result->
                when(result){
                    is Resource.Error<*> -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "Ошибка загрузки профиля"
                        )
                        _effects.emit(ProfileEffect.Error(result.message ?: "Неизвестная ошибка"))
                    }
                    is Resource.Loading<*> -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success<*> -> {
                        val data = result.data!!
                        val mockUser = UserDto(
                            id = 1,
                            name = data.name,
                            phone = data.phone,
                            created_at = data.createdAt,
                            avatar = listOf(
                                AvatarDto(
                                    id = 1,
                                    original_name = "avatar.jpg",
                                    url = "https://example.com/avatar.jpg",
                                    created_at = "2024-01-20"
                                )
                            )
                        )

                        _state.value = _state.value.copy(
                            user = mockUser,
                            isLoading = false
                        )
                    }
                }
            }

        }
    }

    private fun logout() {
        viewModelScope.launch {
            _state.update { ProfileVMState() }
            viewModelScope.launch {
                logoutUserUseCase.execute()
                delay(100)
            }
            // Отправляем эффект для навигации
            _effects.emit(ProfileEffect.Logout)
        }
    }
}