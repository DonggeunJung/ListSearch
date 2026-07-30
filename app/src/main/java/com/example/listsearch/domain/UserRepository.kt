package com.example.listsearch.domain

import com.example.listsearch.data.User
import com.example.listsearch.db.UserDatabase
import com.example.listsearch.network.ApiService
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository @Inject constructor(val apiService: ApiService, val db: UserDatabase) {
    fun fetchUsers(): Flow<List<User>> = flow {
        val dao = db.userDao()
        var users = listOf<User>()
        try {
            users = dao.getAllUsers()
            if(users.isEmpty()) {
                users = apiService.getUsers()
                dao.insertUser(users)
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        emit(users)
    }.flowOn(Dispatchers.IO)
}