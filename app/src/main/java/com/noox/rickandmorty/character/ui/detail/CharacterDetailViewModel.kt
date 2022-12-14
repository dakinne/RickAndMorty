package com.noox.rickandmorty.character.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.domain.usecase.GetCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterId: Int,
    private val getCharacter: GetCharacter
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val character: Character) : UiState()
        object Error : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            getCharacter(characterId).fold(
                onSuccess = { _uiState.emit(UiState.Success(it)) },
                onFailure = { _uiState.emit(UiState.Error) }
            )
        }
    }

}
