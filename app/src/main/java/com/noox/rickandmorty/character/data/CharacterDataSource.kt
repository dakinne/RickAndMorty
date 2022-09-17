package com.noox.rickandmorty.character.data

import com.noox.rickandmorty.core.api.ApiService
import com.noox.rickandmorty.core.data.Result

class CharacterDataSource(
    private val service: ApiService
) {

    suspend fun getCharacter(id: Int) : Result<CharacterDTO> {
        return try {
            Result.Success(service.getCharacter(id))
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }

}
