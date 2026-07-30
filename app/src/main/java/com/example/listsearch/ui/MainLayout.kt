package com.example.listsearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.listsearch.data.User
import com.example.listsearch.data.UserState
import com.example.listsearch.view.UserViewModel

@Composable
fun MainLayout(modifier: Modifier = Modifier, viewModel: UserViewModel) {
    val userState = viewModel.userState.collectAsStateWithLifecycle()
    val searchText = rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                modifier = Modifier.width(200.dp),
                placeholder = { Text(text = "Search users...") }
            )
            Button(modifier = Modifier.padding(20.dp), onClick = {
                viewModel.searchUser(searchText.value)
            }) {
                Text(text = "Search")
            }
        }
        when(userState.value) {
            is UserState.Success -> {
                UserList(users = (userState.value as UserState.Success).users)
            }
            else -> {
            }
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) {
            UserItem(it)
            HorizontalDivider(
                modifier = Modifier.padding(top=10.dp, bottom = 10.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = user.name, fontSize = 20.sp)
        Text(text = user.email, fontSize = 16.sp, color = Color.Gray)
        Text(text = user.company.name, fontSize = 16.sp, color = Color.Gray)
    }
}