package com.techhaal.little_lemon

import android.content.Context

object SharedPreferencesHelper {
    const val PREF_NAME = "MyAppPrefs"
    const val KEY_FIRST_NAME = "firstName"
    const val KEY_LAST_NAME = "lastName"
    const val KEY_EMAIL = "email"
    const val KEY_APP_LAUNCHED = "appLaunched"

    fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_FIRST_NAME, firstName)
        editor.putString(KEY_LAST_NAME, lastName)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getUserData(context: Context): Triple<String?, String?, String?> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, null)
        val lastName = sharedPreferences.getString(KEY_LAST_NAME, null)
        val email = sharedPreferences.getString(KEY_EMAIL, null)
        return Triple(firstName, lastName, email)
    }

    fun isAppLaunched(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_APP_LAUNCHED, false)
    }

    fun setAppLaunched(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_APP_LAUNCHED, true)
        editor.apply()
    }

    fun clearUserData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}