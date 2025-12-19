package com.example.messanger.presentation.profile_feature.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.messanger.data.network.dto.user.UserDto
import com.example.messanger.presentation.profile_feature.model.ProfileVMState

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            state = ProfileVMState(
                user = UserDto(
                    id = 1,
                    name = "Алексей Петров",
                    phone = "+7 (999) 123-45-67",
                    created_at = "15 января 2024",
                    avatar = emptyList()
                )
            ),
            onLogout = {},
            onReload = {}
        )
    }
}