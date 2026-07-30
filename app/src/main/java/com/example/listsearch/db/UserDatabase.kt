package com.example.listsearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listsearch.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
