package com.example.messanger.presentation.naviagtion

sealed class Screens(
    val route:String
) {
    object Register : Screens("register_screen")
    object Chat : Screens("chat_screen")
    object Profile : Screens("profile_screen")
}