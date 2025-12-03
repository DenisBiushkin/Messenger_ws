package com.example.messanger.presentation.profile_feature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.messanger.data.network.dto.AvatarDto
import com.example.messanger.data.network.dto.UserDto
import com.example.messanger.presentation.profile_feature.components.EmptyProfile
import com.example.messanger.presentation.profile_feature.components.LoadingProfile

@Composable
    fun ProfileScreen(
    user: UserDto? = null,
    onLogout: () -> Unit,
    onEditProfile: () -> Unit = {},
    onAddAvatar: () -> Unit = {},
    onAvatarClick: (AvatarDto) -> Unit = {}
    ) {
        var isLoading by remember { mutableStateOf(false) }

        LaunchedEffect(user) {
            isLoading = user == null
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            EmptyProfile(onLogout = onLogout)
//            if (isLoading) {
//                LoadingProfile()
//            } else if (user != null) {
//
//
////                ProfileContent(
////                    user = user,
////                    onLogout = onLogout,
////                    onEditProfile = onEditProfile,
////                    onAddAvatar = onAddAvatar,
////                    onAvatarClick = onAvatarClick
////                )
//
//            } else {
//                EmptyProfile(onLogout = onLogout)
//            }
        }
    }

