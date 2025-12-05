package com.example.messanger.domain.model

data class RegisterUser(
   val username: String,
   val phone:String,
   val password:String,
   val passwordConfirmation:String
)