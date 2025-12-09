package com.example.messanger.presentation.profile_feature.screens

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

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            EmptyProfile(onLogout = onLogout)

        }
    }

