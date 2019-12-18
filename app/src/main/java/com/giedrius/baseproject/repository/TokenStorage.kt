package com.giedrius.baseproject.repository

interface TokenStorage {

    fun saveToken(token: String)

    fun getToken(): String

    fun removeToken()
}
