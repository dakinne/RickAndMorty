package com.noox.rickandmorty.character.di

import com.noox.rickandmorty.character.data.CharacterPagingSource
import com.noox.rickandmorty.character.data.CharacterRepository
import com.noox.rickandmorty.character.data.CharactersMapper
import com.noox.rickandmorty.character.domain.usecase.GetCharacters
import com.noox.rickandmorty.character.ui.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    viewModel {
        CharacterListViewModel(
            getCharacters = get()
        )
    }
    single { GetCharacters(repository = get()) }
    single { CharactersMapper() }
    single { CharacterRepository(dataSource = get(), mapper = get()) }
    single { CharacterPagingSource(service = get()) }
}
