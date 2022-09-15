package com.noox.rickandmorty.character.data

import com.noox.rickandmorty.core.api.ApiService

class CharacterDataSource(
    private val service: ApiService
) {

    suspend fun getCharacter(id: Int) : Result<CharacterDTO> {
        return try {
            Result.success(service.getCharacter(id))
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }

}
