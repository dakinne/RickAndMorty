package com.noox.rickandmorty.character.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.noox.rickandmorty.core.api.ApiService
import retrofit2.HttpException
import java.io.IOException

const val INITIAL_PAGE = 1

class CharacterPagingSource(
    private val service: ApiService
) : PagingSource<Int, CharacterDTO>() {

    var query: String = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {

        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = service.getCharacters(page, query)

            LoadResult.Page(
                data = response.characters,
                prevKey = null, // Only paging forward.
                nextKey = if (response.info.next == null) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? = null

}