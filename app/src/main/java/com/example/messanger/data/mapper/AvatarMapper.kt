package com.example.messanger.data.mapper

import com.example.messanger.data.network.dto.user.AvatarDto
import com.example.messanger.domain.mapper.Mapper
import com.example.messanger.domain.model.Avatar

class AvatarMapper: Mapper<AvatarDto, Avatar> {
    override fun fromFirstToSecond(first: AvatarDto): Avatar {
       return first.run {
           Avatar(
               id = id,
               originalName = original_name,
               url = url,
               createdAt = created_at
           )
       }
    }

    override fun fromSecondToFirst(second: Avatar): AvatarDto {
        throw RuntimeException("У  тебя маппер не дописан дурында")
    }
}