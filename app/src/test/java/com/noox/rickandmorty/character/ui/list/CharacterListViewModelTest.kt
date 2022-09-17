package com.noox.rickandmorty.character.ui.list

import androidx.paging.PagingData
import androidx.paging.map
import com.noox.rickandmorty.character.domain.usecase.GetCharacters
import com.noox.rickandmorty.util.Constants
import com.noox.rickandmorty.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getCharacters = mockk<GetCharacters>()
    private lateinit var viewModel: CharacterListViewModel

    private val constants by lazy { Constants() }
    private val pagingData = PagingData.from(listOf(constants.character1))

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = CharacterListViewModel (
            getCharacters = getCharacters
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenGetCharacters_ThenFlowIsReturned() {
        Assert.assertNotNull(viewModel.characters)
    }

    @Test
    fun whenCharacters_ThenGetCharactersIsCalledWithEmptyQueryValue() = runTest {
        coEvery { getCharacters.invoke("") } returns flowOf( pagingData )
        val pagingData = viewModel.characters.first()
        pagingData.map {

        }
        coVerify { getCharacters.invoke("") }
    }

    @Test
    fun whenSearchByQuery_ThenGetCharactersIsCalledWithQueryValue() = runTest {
        val query = "QUERY"
        coEvery { getCharacters.invoke(query) } returns flowOf( pagingData )

        viewModel.search(query)
        viewModel.characters.first()

        coVerify { getCharacters.invoke(query) }
    }

}


