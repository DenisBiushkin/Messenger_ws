package com.example.messanger.presentation.naviagtion.routes

sealed class Screens(
    val route:String
) {
    object Register : Screens("register_screen")
    object Login : Screens("login_screen")

}