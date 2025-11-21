package com.example.messanger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.messanger.presentation.naviagtion.RootNavigationGraph
import com.example.messanger.presentation.naviagtion.Screens
import dagger.hilt.android.AndroidEntryPoint

// Material Componen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

// Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavHostController

// Compose UI

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navHostController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController = navHostController)
                }
            ) { innerPadding ->
                RootNavigationGraph(
                    navHostController = navHostController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

    }
}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        MainScreen.Chat, MainScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon!!,
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
sealed class MainScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Chat : MainScreen("chat_screen", "Чат", Icons.Default.Email)
    object Profile : MainScreen("profile_screen", "Профиль", Icons.Default.Person)
    object Settings : MainScreen("settings_screen", "Настройки", Icons.Default.Settings)
}