package com.example.listsearch.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    @Embedded(prefix = "company_") val company: Company
)

data class Company(val name: String, val catchPhrase: String, val bs: String)
