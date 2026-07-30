package com.example.listsearch.network

import com.example.listsearch.data.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}