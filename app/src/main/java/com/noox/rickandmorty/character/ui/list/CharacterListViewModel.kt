package com.noox.rickandmorty.character.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.domain.usecase.GetCharacters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class CharacterListViewModel(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private var currentQuery: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val characters: Flow<PagingData<Character>> =
        currentQuery
            .flatMapLatest { getCharacters(it) }
            .cachedIn(viewModelScope)

    fun search(query: String) {
        currentQuery.value = query
    }

}
