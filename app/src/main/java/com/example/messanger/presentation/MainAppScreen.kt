package com.example.messanger.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.messanger.BottomNavigationBar

@Composable
fun MainAppScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        // Вложенный NavHost для экранов с bottom navigation
        NavHost(
            navController = navController,
            startDestination = BottomScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomScreen.Home.route) {
                HomeScreen(onLogout = onLogout)
            }
            composable(BottomScreen.Search.route) {
                SearchScreen()
            }
            composable(BottomScreen.Profile.route) {
                ProfileScreen(onLogout = onLogout)
            }
        }
    }
}