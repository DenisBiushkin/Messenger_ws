package com.example.messanger.data.source

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [
        UserEntity::class
               ],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract val userDao: UserDao
}