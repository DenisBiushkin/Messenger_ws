package com.example.messanger.presentation.login_feature.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.usecases.auth.SignInUseCase
import com.example.messanger.domain.validation.AuthValidator
import com.example.messanger.domain.validation.ValidationResult
import com.example.messanger.presentation.login_feature.model.LoginEvent
import com.example.messanger.presentation.login_feature.model.LoginVMState
import com.example.messanger.presentation.login_feature.model.UiEffect
import com.example.messanger.util.Resource
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
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val authValidator: AuthValidator
) : ViewModel() {

    private val _state = MutableStateFlow(LoginVMState());
    val state: StateFlow<LoginVMState> = _state.asStateFlow()

    private val _uiEffects = Channel<UiEffect>()
    val uiEffects = _uiEffects.receiveAsFlow()



//    init {
//       viewModelScope.launch {
//           _uiEffects.send(UiEffect.NavigateToHome)
//       }
//    }
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
                signIn()
            }
            is LoginEvent.ClearState -> {
                _state.update {
                    LoginVMState()
                }
            }
            is LoginEvent.ClearError->{

            }
        }
    }

    private fun signIn(){
        viewModelScope.launch {
            when(val res= authValidator.signInValidate(state.value.phone,state.value.password)){
                is ValidationResult.Error ->{
                    // вывести ошибку
                    _uiEffects.send(UiEffect.ShowError(res.message))
                    return@launch
                }
                is ValidationResult.Success->{
                    // идем дальше
                }
            }
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            when(signInUseCase.execute(state.value.phone,state.value.password)){

                is Resource.Success ->{
                    _state.update { it.copy(isLoading = false, loginSuccess = true) }
                    // Отправляем эффект навигации
                    _uiEffects.send(UiEffect.NavigateToHome)
                    // Автоматически сбрасываем флаг через 1 секунду
                    delay(1000)
                    _state.update { it.copy(loginSuccess = false) }
                }

                is Resource.Error ->{
                    // Ошибка входа
                    _state.update {
                        it.copy(isLoading = false,
                            errorMessage = "Неверный телефон или пароль"
                        )
                    }
                    _uiEffects.send(UiEffect.ShowError("Ошибка входа"))
                }
                is Resource.Loading ->{

                }
            }
        }
    }
}