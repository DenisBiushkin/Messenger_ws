package com.example.messanger.presentation.register_feature.viemodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.data.AuthDataStoreManager
import com.example.messanger.data.network.AuthApi
import com.example.messanger.data.network.MainApi
import com.example.messanger.data.source.AppDatabase
import com.example.messanger.presentation.register_feature.models.RegisterVMState
import com.example.messanger.util.Constants
import com.example.messanger.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val database: AppDatabase,
    private val mainApi: MainApi,
): ViewModel() {
    private val _state: MutableStateFlow<RegisterVMState> = MutableStateFlow(RegisterVMState())
    val state: StateFlow<RegisterVMState> = _state.asStateFlow()

//    init {
//        viewModelScope.launch {
//
//
//                val response= api.getAuthToken(
//                    grantType = Constants.NETWORK_GRANT_TYPE,
//                    clientId = Constants.NETWORK_CLIENT_ID,
//                    clientSecret =  Constants.NETWORK_CLIENT_SECRET,
//                    username = "",
//                    password = "123123123",
//                    scope =  Constants.NETWORK_SCOPE
//                )
//            val res=if (response.isSuccessful) {
//                response.body()?.let { tokenDto ->
//                    Log.d("MyTag",tokenDto.toString())
//                } ?:   Log.d("MyTag","Empty response body")
//            } else {
//                Log.d("MyTag","HTTP error: ${response.code()}")
//            }
//
//            database.userDao.insert(UserEntity(1,"Denis","388238",""))
//        }
//    }
    init{
        test()
    }

    private val ACCESS_TOKEN ="Bearer "

    private fun test(){
        viewModelScope.launch {
//            val token = api.getAuthToken(
//                grantType = Constants.NETWORK_GRANT_TYPE_GET_TOKEN,
//                clientId = Constants.NETWORK_CLIENT_ID,
//                clientSecret = Constants.NETWORK_CLIENT_SECRET,
//                scope = Constants.NETWORK_SCOPE,
//                password = "123123123",
//                username = "9279773278",
//            )
           // tokenManager.saveTokens(token.access_token,token.refresh_token)
            val response = mainApi.getUser()

          //  Log.d(TAG,"Saved TOKEN "+tokenManager.getTokens())
            Log.d(TAG,"Status: ${response}")
           // Log.d(TAG,token.toString())
        }
    }
}