package com.example.messanger.presentation.register_feature.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.UserApi
import com.example.messanger.data.source.AppDatabase
import com.example.messanger.domain.model.RegisterUser
import com.example.messanger.domain.usecases.auth.RegistrationUserUseCase
import com.example.messanger.domain.validation.AuthValidator
import com.example.messanger.domain.validation.ValidationResult
import com.example.messanger.presentation.register_feature.models.RegisterVMState
import com.example.messanger.presentation.register_feature.models.RegistrationEffect
import com.example.messanger.presentation.register_feature.models.RegistrationEvent
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
class RegisterViewModel @Inject constructor(
    private val authValidator: AuthValidator,
    private val registrationUserUseCase: RegistrationUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RegisterVMState())
    val state: StateFlow<RegisterVMState> = _state.asStateFlow()

    private val _effects = Channel<RegistrationEffect>()
    val effects = _effects.receiveAsFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            RegistrationEvent.ClearError -> {
                _state.update { it.copy(errorMessage = null) }
            }

            is RegistrationEvent.NameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name,
                        errorMessage = null
                    )
                }
            }

            is RegistrationEvent.PhoneChanged -> {
                _state.update {
                    it.copy(
                        phone = event.phone,
                        errorMessage = null
                    )
                }
            }

            is RegistrationEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        errorMessage = null
                    )
                }
            }

            is RegistrationEvent.PasswordConfirmationChanged -> {
                _state.update {
                    it.copy(
                        passwordConfirmation = event.passwordConfirmation,
                        errorMessage = null
                    )
                }
            }

            RegistrationEvent.TogglePasswordVisibility -> {
                _state.update { it.copy(passwordVisible = !_state.value.passwordVisible) }
            }

            RegistrationEvent.ToggleConfirmPasswordVisibility -> {
                _state.update { it.copy(confirmPasswordVisible = !_state.value.confirmPasswordVisible) }
            }

            RegistrationEvent.Register -> {
                registerUser()
            }
        }
    }
    private fun  registerUser(){
        viewModelScope.launch {
            val registerUser = buildRegisterUser()
            when(val validateResult = authValidator.signUpValidate(registerUser)){
                is ValidationResult.Error -> {
                    _effects.send(RegistrationEffect.ShowSnackbar(validateResult.message))
                    return@launch
                }
                ValidationResult.Success -> {}
            }

            registrationUserUseCase.execute(registerUser).collect {
                result->
                when(result){
                    is Resource.Error<*> -> {
                        _state.update { it.copy(isLoading = false) }
                        _effects.send(RegistrationEffect.ShowSnackbar(result.message ?:"Ошибка регистрации")) //
                    }
                    is Resource.Loading<*> -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success<*> -> {
                        // После успешной регистрации
                        _state.update { it.copy(isLoading = false) }
                        _effects.send(RegistrationEffect.ShowSnackbar("Успешно!")) //
                        delay(300)
                        _effects.send(RegistrationEffect.NavigateToHome) //
                    }
                }
            }


        }
    }
    private fun buildRegisterUser(): RegisterUser{
        return RegisterUser(
            username = state.value.name,
            phone = state.value.phone,
            password = state.value.password,
            passwordConfirmation = state.value.passwordConfirmation
        )
    }
}