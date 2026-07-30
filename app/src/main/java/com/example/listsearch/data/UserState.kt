package com.example.listsearch.data

sealed class UserState {
    object Loading : UserState()
    data class Success(val users: List<User>): UserState()
    data class Error(val message: String): UserState()
}