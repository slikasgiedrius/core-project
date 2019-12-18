package com.giedrius.baseproject.repository

import android.content.SharedPreferences
import com.giedrius.baseproject.utils.Constants

class PreferencesTokenStorage(
        private val sharedPreferences: SharedPreferences
) : TokenStorage {

    override fun removeToken() {
        sharedPreferences.edit().remove(Constants.KEY_TOKEN).apply()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(Constants.KEY_TOKEN, token).apply()
    }

    override fun getToken(): String = sharedPreferences.getString(Constants.KEY_TOKEN, Constants.EMPTY_TOKEN)!!
}
