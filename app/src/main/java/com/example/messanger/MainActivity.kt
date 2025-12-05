package com.example.messanger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.messanger.presentation.naviagtion.navgraph.RootNavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.messanger.data.token.TokenProvider
import com.example.messanger.presentation.naviagtion.routes.NavRoutes
import com.example.messanger.presentation.naviagtion.routes.NavRoutes.AUTH_GRAPH
import com.example.messanger.util.MinimalSplashScreen
import kotlinx.coroutines.delay
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenProvider: TokenProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var startDestination by remember { mutableStateOf<String>(AUTH_GRAPH) }
            val navHostController = rememberNavController()
            var visible by remember { mutableStateOf(true) }
            //Залогинин ли уже пользователь
            LaunchedEffect(Unit) {

               //УДАЛИТЬ ПОСЛЕ НАСТРОЙКИ
               // tokenProvider.clearTokens()

                val hasSession = tokenProvider.hasValidSession()
                startDestination = if (hasSession) NavRoutes.MAIN_GRAPH else NavRoutes.AUTH_GRAPH
                visible = false
            }

            //УДАЛИТЬ ПОСЛЕ НАСТРОЙКИ
             //   startDestination = NavRoutes.MAIN_GRAPH

            if ( visible) {
              //  MinimalSplashScreen()
            } else {
                RootNavigationGraph(
                    navHostController = navHostController,
                    startDestination = startDestination
                )
            }
        }

    }
}