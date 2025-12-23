package com.example.messanger.presentation.naviagtion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.messanger.presentation.naviagtion.navgraph.MainNavGraph
import com.example.messanger.presentation.naviagtion.routes.MainScreen

@Composable
fun MainAppScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val showBottomBar = when (currentDestination?.route) {
        MainScreen.Chat.getArgumentRoutes(),
            -> false
        else -> true
    }
    Scaffold(
        bottomBar = {
            if(showBottomBar){
                BottomNavigationBar(navController = navController)
            }
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