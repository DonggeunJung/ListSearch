package com.example.listsearch.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listsearch.data.User
import com.example.listsearch.data.UserState
import com.example.listsearch.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(val repository: UserRepository): ViewModel() {
    private var users = listOf<User>()
    val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState

    fun fetchUsers() {
        viewModelScope.launch {
            repository.fetchUsers().catch {
                _userState.value = UserState.Error(it.message ?: "")
            }.collect {
                users = it
                searchUser()
            }
        }
    }

    fun searchUser(query: String = "") {
        if(query.isEmpty()) {
            _userState.value = UserState.Success(users)
        } else {
            val filtered = users.filter {
                it.name.contains(query) || it.email.contains(query) || it.company.name.contains(query)
            }
            _userState.value = UserState.Success(filtered)
        }
    }
}