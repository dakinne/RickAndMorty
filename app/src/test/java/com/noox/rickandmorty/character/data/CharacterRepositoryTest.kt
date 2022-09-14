package com.noox.rickandmorty.character.data

import androidx.paging.PagingData
import com.noox.rickandmorty.Constants
import com.noox.rickandmorty.character.domain.model.Character
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.assertNotNull
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    private val dataSource = mockk<CharacterPagingSource>()
    private val mapper = CharactersMapper()
    private val repository = CharacterRepository(dataSource, mapper)

    private val constants by lazy { Constants() }
    private val loadResultPage = constants.loadResultPage1

    @Test
    fun whenGetCharacters_ThenFlowIsReturned() {
        coEvery { dataSource.load(any()) } returns loadResultPage
        val charactersFlow: Flow<PagingData<Character>> = repository.getCharacters("")
        assertNotNull(charactersFlow)
    }

}
