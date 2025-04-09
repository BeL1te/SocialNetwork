package ru.fillandroid.socialnetwork.domain.repository

interface AuthRepository {

    suspend fun requestCode()

    suspend fun validateCode(code: String)
}