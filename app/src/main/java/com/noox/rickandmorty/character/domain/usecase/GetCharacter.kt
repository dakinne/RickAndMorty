package com.noox.rickandmorty.character.domain.usecase

import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.core.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacter(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Character> = withContext(dispatcher) {
        repository.getCharacter(id)
    }
}
