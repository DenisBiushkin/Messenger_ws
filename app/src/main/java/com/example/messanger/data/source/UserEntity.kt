package com.example.messanger.data.source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name:String,
    val phone:String,
    val phone_verified_at: String?,
)
