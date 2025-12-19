package com.example.messanger.data.mapper

import com.example.messanger.data.network.dto.user.UserDto
import com.example.messanger.domain.mapper.Mapper
import com.example.messanger.domain.model.Avatar
import com.example.messanger.domain.model.User

class UserMapper: Mapper<UserDto, User> {
    override fun fromFirstToSecond(first: UserDto): User {
        return first.run{
            User(
                id= id,
                name= name,
                phone = phone,
                createdAt = created_at,
                avatars =avatar.map{ AvatarMapper().fromFirstToSecond(it)}
            )
        }
    }

    override fun fromSecondToFirst(second: User): UserDto {
        throw RuntimeException("У  тебя маппер не дописан дурында")
    }
}