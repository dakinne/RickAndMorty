package com.noox.rickandmorty.character.domain.usecase

import com.noox.rickandmorty.util.Constants
import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.core.data.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterTest {

    private val repository = mockk<CharacterRepository>()
    private val getCharacter = GetCharacter(
        repository = repository
    )

    private val constants by lazy { Constants() }
    private val character = constants.character1
    private val characterId = 1
    private val exception = Exception()

    @Test
    fun whenGetCharacterIsSuccess_ThenSuccessOfCharacterIsReturned() = runTest {
        coEvery { repository.getCharacter(characterId) } returns Result.Success(character)
        val result = getCharacter(characterId)
        Assert.assertEquals(Result.Success(character), result)
    }

    @Test
    fun whenGetCharacterIsFailed_ThenErrorIsReturned() = runTest {
        coEvery { repository.getCharacter(characterId) } returns Result.Failure(exception)
        val result = repository.getCharacter(characterId)
        Assert.assertEquals(Result.Failure(exception), result)
    }

}
