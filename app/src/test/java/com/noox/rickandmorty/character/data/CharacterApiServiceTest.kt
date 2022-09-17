package com.noox.rickandmorty.character.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.noox.rickandmorty.util.Constants
import com.noox.rickandmorty.core.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class CharacterApiServiceTest {

    private lateinit var apiService: ApiService

    private lateinit var mockWebServer: MockWebServer

    private val constants by lazy { Constants() }
    private val charactersPage = constants.jsonOfCharactersPage1
    private val expectedCharactersPageDTO = constants.charactersPageDTO1

    private val emptyCharactersPage = constants.jsonOfEmptyCharactersPage1
    private val expectedEmptyCharactersPageDTO = constants.emptyCharactersPageDTO1

    private val character = constants.jsonOfCharacter1
    private val expectedCharacterDTO = constants.characterDTO1

    @Before
    fun createService() {
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun loadCharacters() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(charactersPage)
        )
        val charactersPageDTO = apiService.getCharacters(1, "")

        assertNotNull(charactersPageDTO)
        assertEquals(expectedCharactersPageDTO, charactersPageDTO)
    }

    @Test
    fun loadEmptyCharacters() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(emptyCharactersPage)
        )
        val charactersPageDTO = apiService.getCharacters(1, "")

        assertNotNull(charactersPageDTO)
        assertEquals(expectedEmptyCharactersPageDTO, charactersPageDTO)
    }

    @Test
    fun loadCharacter() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(character)
        )
        val characterDTO = apiService.getCharacter(1)

        assertNotNull(characterDTO)
        assertEquals(expectedCharacterDTO, characterDTO)
    }
}
