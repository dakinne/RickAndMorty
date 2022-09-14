package com.noox.rickandmorty.character.domain.usecase

import androidx.paging.PagingData
import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.character.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharacters(
    private val repository: CharacterRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Character>> = repository.getCharacters(query)
}
