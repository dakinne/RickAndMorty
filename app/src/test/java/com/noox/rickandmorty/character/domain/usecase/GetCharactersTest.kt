package com.noox.rickandmorty.character.domain.usecase

import androidx.paging.PagingData
import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.character.domain.model.Character
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test

class GetCharactersTest {

    private val repository = mockk<CharacterRepository>()
    private val getCharacters = GetCharacters(repository)

    private val pagingData = mockk<PagingData<Character>>()

    @Test
    fun whenGetCharacters_ThenFlowIsReturned() {
        coEvery { repository.getCharacters(any()) } returns flowOf(pagingData)
        val charactersFlow: Flow<PagingData<Character>> = getCharacters("")
        Assert.assertNotNull(charactersFlow)
    }

}
