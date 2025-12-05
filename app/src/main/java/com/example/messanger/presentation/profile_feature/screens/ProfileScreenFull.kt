package com.example.messanger.presentation.profile_feature.screens

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.profile_feature.model.ProfileEffect
import com.example.messanger.presentation.profile_feature.model.ProfileEvent
import com.example.messanger.presentation.profile_feature.viewmodel.ProfileScreenViewModel
import com.example.messanger.presentation.register_feature.models.RegistrationEffect
import kotlinx.coroutines.launch

@Composable
fun ProfileScreenFull(
    viewModel: ProfileScreenViewModel = hiltViewModel<ProfileScreenViewModel>(),
    onLogout:()->Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                ProfileEffect.ErrorAlert -> {

                }
                ProfileEffect.Logout -> {
                    onLogout()
                }
            }
        }
    }

    ProfileScreen(
        onLogout = {
                viewModel.onEvent(ProfileEvent.Logout)
        },
    )
}