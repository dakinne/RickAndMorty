package com.noox.rickandmorty.character.di

import com.noox.rickandmorty.character.data.CharacterDataSource
import com.noox.rickandmorty.character.data.CharacterPagingSource
import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.character.data.CharactersMapper
import com.noox.rickandmorty.character.domain.usecase.GetCharacter
import com.noox.rickandmorty.character.domain.usecase.GetCharacters
import com.noox.rickandmorty.character.ui.detail.CharacterDetailViewModel
import com.noox.rickandmorty.character.ui.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    viewModel {
        CharacterListViewModel(
            getCharacters = get()
        )
    }
    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(
            characterId = characterId,
            getCharacter = get()
        )
    }
    single { GetCharacters(repository = get()) }
    single { GetCharacter(repository = get()) }
    single { CharactersMapper() }
    single { CharacterRepository(pagingSource = get(), dataSource = get(), mapper = get()) }
    single { CharacterPagingSource(service = get()) }
    single { CharacterDataSource(service = get()) }
}
