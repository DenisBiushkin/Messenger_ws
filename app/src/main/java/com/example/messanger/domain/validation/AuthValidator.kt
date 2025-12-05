package com.example.messanger.domain.validation

import com.example.messanger.domain.model.RegisterUser

interface AuthValidator {

    fun signInValidate(phone:String,password:String): ValidationResult

    fun signUpValidate(registerUser: RegisterUser): ValidationResult

}