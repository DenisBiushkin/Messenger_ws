package com.example.messanger.domain.validation

import android.util.Patterns
import com.example.messanger.domain.model.RegisterUser

class AuthValidatorImpl:AuthValidator {

    override fun signInValidate(
        phone: String,
        password: String
    ): ValidationResult {
        val phoneResult = validatePhone(phone)
        val passwordResult = validatePassword(password)
        if (
            (phoneResult is ValidationResult.Success
            &&
            passwordResult is ValidationResult.Success)
        ){
            return ValidationResult.Success
        }
        return ValidationResult.Error("Некорректные данные")
    }

    override fun signUpValidate(
        registerUser: RegisterUser
    ): ValidationResult {
        val phoneResult = validatePhone(registerUser.phone)
        val passwordResult = validatePasswordMatch(registerUser.password,registerUser.passwordConfirmation)
        val usernameResult =validateUsername(registerUser.username)
        if (
            (phoneResult is ValidationResult.Success
                    &&
                    passwordResult is ValidationResult.Success
                    &&
                    usernameResult is ValidationResult.Success
                    )
        ){
            return ValidationResult.Success
        }
        return ValidationResult.Error("Некорректные данные")
    }

    private fun validatePhone(phone: String): ValidationResult {
        return when {
            phone.isBlank() -> ValidationResult.Error("Номер телефона обязателен")
            !isValidPhoneFormat(phone) -> ValidationResult.Error("Неверный формат телефона")
            else -> ValidationResult.Success
        }
    }

    private fun isValidPhoneFormat(phone: String): Boolean {
        // Очищаем от лишних символов
        val cleanPhone = phone.replace(Regex("[\\s()-]"), "")

        // Проверяем российские номера (пример)
        return when {
            // +7XXXXXXXXXX или 8XXXXXXXXXX
            cleanPhone.matches(Regex("^(\\+7|8)?\\d{10}\$")) -> true

            // Международный формат (пример)
            cleanPhone.matches(Regex("^\\+\\d{10,15}\$")) -> true

            else -> false
        }
    }

    private fun validatePassword(
        password: String
    ): ValidationResult{
        return when {
            password.isBlank() -> ValidationResult.Error("Параль обязателен")
            password.length > 7 -> ValidationResult.Error("Пароль минимум 8 символов")
            else -> ValidationResult.Success
        }
    }

    // Проверка совпадения паролей
    private  fun validatePasswordMatch(
        password: String,
        confirmPassword: String
    ): ValidationResult {
        return if (password != confirmPassword) {
            ValidationResult.Error("Пароли не совпадают")
        } else {
            return validatePassword(password)
        }
    }

    // Проверка имени пользователя
    private fun validateUsername(username: String): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult.Error("Имя пользователя обязательно")
            username.length < 3 -> ValidationResult.Error("Имя минимум 3 символа")
            username.length > 20 -> ValidationResult.Error("Имя максимум 20 символов")
            !username.matches(Regex("^[a-zA-Z0-9_]+\$")) ->
                ValidationResult.Error("Только буквы, цифры и подчёркивание")
            else -> ValidationResult.Success
        }
    }
}