package com.noox.rickandmorty.character.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.noox.rickandmorty.character.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 20

class CharacterRepository(
    private val dataSource: CharacterPagingSource,
    private val mapper: CharactersMapper
) {

    fun getCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dataSource.query = query
                dataSource
            }
        )
            .flow
            .map { pagingData ->
                pagingData.map { characterDTO ->
                    mapper.mapToModel(characterDTO)
                }
            }
    }

}
