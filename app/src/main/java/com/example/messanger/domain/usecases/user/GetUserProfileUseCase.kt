package com.example.messanger.domain.usecases.user

import com.example.messanger.domain.repository.UserRepository

class GetUserProfileUseCase (
    private val userRepository: UserRepository
) {
//    suspend operator fun invoke(): Result<UserProfile> {
//        val userResult = userRepository.getCurrentUser()
//
//        return when (userResult) {
//            is Result.Success -> {
//                // Можно добавить статистику и другую бизнес-логику
//                val profile = UserProfile(user = userResult.data)
//                Result.Success(profile)
//            }
//            is Result.Error -> userResult
//        }
//    }
}