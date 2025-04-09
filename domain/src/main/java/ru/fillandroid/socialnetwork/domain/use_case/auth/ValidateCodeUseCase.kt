package ru.fillandroid.socialnetwork.domain.use_case.auth

import ru.fillandroid.socialnetwork.domain.repository.AuthRepository

class ValidateCodeUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(code: String) = repository.validateCode(code)
}