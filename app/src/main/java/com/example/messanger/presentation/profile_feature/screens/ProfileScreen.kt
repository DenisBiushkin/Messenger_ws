package com.example.messanger.presentation.profile_feature.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

