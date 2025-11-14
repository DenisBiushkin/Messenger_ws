package com.example.messanger.presentation.register_feature.viemodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.source.AppDatabase
import com.example.messanger.data.source.UserEntity
import com.example.messanger.presentation.register_feature.models.RegisterVMState
import com.example.messanger.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val database: AppDatabase,
    private val api: AuthApi
): ViewModel() {
    private val _state: MutableStateFlow<RegisterVMState> = MutableStateFlow(RegisterVMState())
    val state: StateFlow<RegisterVMState> = _state.asStateFlow()

    init {
        viewModelScope.launch {


                val response= api.getAuthToken(
                    grantType = Constants.NETWORK_GRANT_TYPE,
                    clientId = Constants.NETWORK_CLIENT_ID,
                    clientSecret =  Constants.NETWORK_CLIENT_SECRET,
                    username = "",
                    password = "123123123",
                    scope =  Constants.NETWORK_SCOPE
                )
            val res=if (response.isSuccessful) {
                response.body()?.let { tokenDto ->
                    Log.d("MyTag",tokenDto.toString())
                } ?:   Log.d("MyTag","Empty response body")
            } else {
                Log.d("MyTag","HTTP error: ${response.code()}")
            }

            database.userDao.insert(UserEntity(1,"Denis","388238",""))
        }
    }
}