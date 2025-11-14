package com.example.messanger.presentation.naviagtion

sealed class Screens(
    val route:String
) {
    object Register : Screens("register_screen")
}