package com.example.messanger.data.mapper

import com.example.messanger.data.network.dto.RegisterRequest
import com.example.messanger.domain.mapper.Mapper
import com.example.messanger.domain.model.RegisterUser

class RegisterUserMapper : Mapper<RegisterUser, RegisterRequest> {

    override fun fromFirstToSecond(first: RegisterUser): RegisterRequest {
        return first.run {
            RegisterRequest(
                name = username,
                phone = phone,
                password = password,
                passwordConfirmation = passwordConfirmation
            )
        }
    }

    override fun fromSecondToFirst(second: RegisterRequest): RegisterUser {
       throw RuntimeException("У  тебя маппер не дописан дурында")
    }
}