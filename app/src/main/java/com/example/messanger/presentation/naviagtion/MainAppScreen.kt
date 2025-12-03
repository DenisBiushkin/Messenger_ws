package com.example.messanger.presentation.naviagtion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.messanger.presentation.naviagtion.navgraph.MainNavGraph

@Composable
fun MainAppScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        // Вложенный NavHost для экранов с bottom navigation
        MainNavGraph(
            navController = navController ,
            modifier = Modifier.padding(innerPadding),
            onLogout = onLogout
        )
    }
}