package com.noox.rickandmorty.character.ui.list

import com.noox.rickandmorty.character.domain.usecase.GetCharacter
import com.noox.rickandmorty.character.ui.detail.CharacterDetailViewModel
import com.noox.rickandmorty.character.ui.detail.CharacterDetailViewModel.UiState
import com.noox.rickandmorty.core.data.Result
import com.noox.rickandmorty.util.Constants
import com.noox.rickandmorty.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getCharacter = mockk<GetCharacter>()
    private val characterId = 1

    private val constants by lazy { Constants() }
    private val character = constants.character1

    @Test
    fun whenCreateViewModel_ThenGetCharacterIsCalled() = runTest {
        coEvery { getCharacter(characterId) } coAnswers { Result.Success(character) }

        createViewModel()

        coVerify(exactly = 1) { getCharacter(characterId) }
    }

    @Test
    fun whenCreateViewModel_AndLoadCharacterSuccessful_ThenUiStateIsLoading_AndChangeToSuccess() = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.Success(character)
        }

        val viewModel = createViewModel()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Success)

        job.cancel()
    }

    @Test
    fun whenCreateViewModel_AndLoadCharacterFailed_ThenUiStateIsLoading_AndChangeToError() = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.Failure(Exception())
        }

        val viewModel = createViewModel()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Error)

        job.cancel()
    }

    @Test
    fun whenLoadCharacter_ThenGetCharacterIsCalled() = runTest {
        coEvery { getCharacter(characterId) } coAnswers { Result.Success(character) }

        val viewModel = createViewModel()
        viewModel.loadCharacter()

        coVerify(exactly = 2) { getCharacter(characterId) }
    }

    @Test
    fun whenLoadCharacterSuccessful_ThenUiStateIsLoading_AndChangeToSuccess() = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.Success(character)
        }

        val viewModel = createViewModel()
        viewModel.loadCharacter()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Success)

        job.cancel()
    }

    @Test
    fun whenLoadCharacterFailed_ThenUiStateIsLoading_AndChangeToError() = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.Failure(Exception())
        }

        val viewModel = createViewModel()
        viewModel.loadCharacter()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Error)

        job.cancel()
    }

    private fun createViewModel() = CharacterDetailViewModel(
        characterId = characterId,
        getCharacter = getCharacter
    )

}
