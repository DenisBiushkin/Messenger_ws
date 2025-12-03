package com.example.messanger.presentation.naviagtion.routes

sealed class Screens(
    val route:String
) {
    object Register : Screens("register_screen")
    object Chat : Screens("chat_screen")
    object Profile : Screens("profile_screen")
}