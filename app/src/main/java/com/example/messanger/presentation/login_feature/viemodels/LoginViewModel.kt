package com.example.messanger.presentation.login_feature.viemodels

import androidx.lifecycle.ViewModel
import com.example.messanger.presentation.login_feature.model.LoginEvent
import com.example.messanger.presentation.login_feature.model.LoginVMState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(LoginVMState());
    val state: StateFlow<LoginVMState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.PhoneChanged -> _state.update {
                it.copy(phone = event.phone)
            }
            is LoginEvent.PasswordChanged -> _state.update {
                it.copy(password = event.password)
            }
            is LoginEvent.TogglePasswordVisibility -> _state.update {
                it.copy(passwordVisible = !it.passwordVisible)
            }
            is LoginEvent.OnLogin ->{

            }
        }
    }
}