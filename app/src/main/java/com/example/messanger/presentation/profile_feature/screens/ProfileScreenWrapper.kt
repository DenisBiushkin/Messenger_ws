package com.example.messanger.presentation.profile_feature.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messanger.presentation.profile_feature.model.ProfileEffect
import com.example.messanger.presentation.profile_feature.model.ProfileEvent
import com.example.messanger.presentation.profile_feature.viewmodel.ProfileScreenViewModel

@Composable
fun ProfileScreenFull(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.onEvent(ProfileEvent.LoadProfile)
    }
    
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ProfileEffect.Logout -> onLogout()
                is ProfileEffect.Error -> {
                    // Можно показать Snackbar или AlertDialog
                }
            }
        }
    }
    
    ProfileScreen(
        modifier = modifier,
        state = state,
        onLogout = { viewModel.onEvent(ProfileEvent.Logout) },
        onReload = { viewModel.onEvent(ProfileEvent.LoadProfile) }
    )
}