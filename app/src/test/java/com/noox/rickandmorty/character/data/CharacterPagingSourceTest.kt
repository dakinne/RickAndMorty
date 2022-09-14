package com.noox.rickandmorty.character.data

import androidx.paging.PagingSource
import com.noox.rickandmorty.core.api.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class CharacterPagingSourceTest {

    private val apiService = mockk<ApiService>()
    private val pagingSource = CharacterPagingSource(apiService)

    private val characterPageDTO = CharactersPageDTO(
        info = InfoDTO(pages = 0, next = null, prev = null), characters = emptyList()
    )
    private val query = ""

    @Test
    fun givenNullKey_WhenLoad_ThenGetCharactersOfPage1() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } returns characterPageDTO

        pagingSource.load(loadParamWithKey(null))

        coVerify { apiService.getCharacters(1, query) }
    }

    @Test
    fun given1AsKey_WhenLoad_ThenGetCharactersOfPage1() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } returns characterPageDTO

        pagingSource.load(loadParamWithKey(1))

        coVerify { apiService.getCharacters(1, query) }
    }

    @Test
    fun given2AsKey_WhenLoad_ThenGetCharactersOfPage2() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } returns characterPageDTO

        pagingSource.load(loadParamWithKey(2))

        coVerify { apiService.getCharacters(2, query) }
    }

    @Test
    fun givenNotEmptyQuery_WhenLoad_ThenGetCharactersFiltersByQuery() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } returns characterPageDTO

        val query = "Testing"
        pagingSource.query = query
        pagingSource.load(loadParamWithKey(2))

        coVerify { apiService.getCharacters(2, query) }
    }

    @Test
    fun whenCallIsSuccessful_ThenReturnLoadResultPage() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } returns characterPageDTO

        val loadResult = pagingSource.load(loadParamWithKey(1))

        Assert.assertTrue(loadResult is PagingSource.LoadResult.Page)
    }

    @Test
    fun whenCallFailureWithIOException_ThenReturnLoadResultError() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } throws IOException()

        val loadResult = pagingSource.load(loadParamWithKey(1))

        Assert.assertTrue(loadResult is PagingSource.LoadResult.Error)
    }

    @Test
    fun whenCallFailureWithHttpException_ThenReturnLoadResultError() = runTest {
        coEvery { apiService.getCharacters(any(), any()) } throws httpException()

        val loadResult = pagingSource.load(loadParamWithKey(1))

        Assert.assertTrue(loadResult is PagingSource.LoadResult.Error)
    }

    private fun httpException() = HttpException(
        Response.error<Any>(400, "error".toResponseBody("plain/text".toMediaTypeOrNull()))
    )

    private fun loadParamWithKey(key: Int?) = PagingSource.LoadParams.Refresh(
        key = key,
        loadSize = 2,
        placeholdersEnabled = false
    )

}