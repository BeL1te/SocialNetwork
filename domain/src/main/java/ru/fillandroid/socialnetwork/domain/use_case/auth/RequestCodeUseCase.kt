package ru.fillandroid.socialnetwork.domain.use_case.auth

import ru.fillandroid.socialnetwork.domain.repository.AuthRepository

class RequestCodeUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.requestCode()
}
