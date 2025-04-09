package ru.fillandroid.socialnetwork.data.repository

import ru.fillandroid.socialnetwork.data.data_source.AuthDao
import ru.fillandroid.socialnetwork.data.data_source.data.AuthEntity
import ru.fillandroid.socialnetwork.domain.repository.AuthRepository
import java.util.UUID

class AuthRepositoryImpl(
    private val authDao: AuthDao
) : AuthRepository {

    // Имитация запроса на СМС, вместо кода просто добавляем его в таблицу
    override suspend fun requestCode() {
        authDao.insertCode(AuthEntity(id = UUID.randomUUID().toString(), code = "1234"))
    }

    // Имитация запроса 204 при верном коде или кода 400 при несовпадении
    override suspend fun validateCode(code: String) {
        if (authDao.requestCode() != code) throw IllegalStateException()
    }
}
