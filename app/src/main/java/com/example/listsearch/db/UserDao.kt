package com.example.listsearch.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.listsearch.data.User

@Dao
interface UserDao {
    @Upsert
    suspend fun insertUser(users: List<User>)

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>
}
