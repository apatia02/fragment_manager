package com.example.fragment_manager.domain

interface UserShared {
    fun saveUserName(username: String): Unit

    fun getUserName(): String

    fun clearUserName(): Unit
}