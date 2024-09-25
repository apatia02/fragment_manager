package com.example.fragment_manager.data

import android.content.Context
import android.content.SharedPreferences
import com.example.fragment_manager.domain.EMPTY_STRING
import com.example.fragment_manager.domain.UserShared

private const val PREF_NAME = "user_prefs"
private const val KEY_USERNAME = "username"

fun UserShared(context: Context): UserShared {
    return object : UserShared {

        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        override fun saveUserName(username: String) {
            val editor = sharedPreferences.edit()
            editor.putString(KEY_USERNAME, username)
            editor.apply()
        }

        override fun getUserName(): String {
            return sharedPreferences.getString(KEY_USERNAME, EMPTY_STRING) ?: EMPTY_STRING
        }

        override fun clearUserName() {
            val editor = sharedPreferences.edit()
            editor.putString(KEY_USERNAME, EMPTY_STRING)
            editor.apply()
        }
    }
}