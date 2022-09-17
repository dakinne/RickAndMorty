package com.noox.rickandmorty.character.data

import androidx.paging.PagingData
import com.noox.rickandmorty.util.Constants
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.core.data.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    private val pagingSource = mockk<CharacterPagingSource>()
    private val dataSource = mockk<CharacterDataSource>()
    private val mapper = CharactersMapper()
    private val repository = CharacterRepository(pagingSource, dataSource, mapper)

    private val constants by lazy { Constants() }
    private val loadResultPage = constants.loadResultPage1
    private val characterDTO = constants.characterDTO1
    private val character = constants.character1
    private val characterId = 1
    private val exception = Exception()

    @Test
    fun whenGetCharacters_ThenFlowIsReturned() {
        coEvery { pagingSource.load(any()) } returns loadResultPage
        val charactersFlow: Flow<PagingData<Character>> = repository.getCharacters("")
        assertNotNull(charactersFlow)
    }

    @Test
    fun whenGetCharacterIsSuccess_ThenSuccessOfCharacterIsReturned() = runTest {
        coEvery { dataSource.getCharacter(any()) } returns Result.Success(characterDTO)
        val result = repository.getCharacter(1)
        assertEquals(Result.Success(character), result)
    }

    @Test
    fun whenGetCharacterIsFailed_ThenErrorIsReturned() = runTest {
        coEvery { dataSource.getCharacter(characterId) } returns Result.Failure(exception)
        val result = repository.getCharacter(characterId)
        assertEquals(Result.Failure(exception), result)
    }

}
