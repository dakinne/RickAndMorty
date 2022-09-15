package com.noox.rickandmorty.core.api

import com.noox.rickandmorty.character.data.CharacterDTO
import com.noox.rickandmorty.character.data.CharactersPageDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 0,
        @Query("name") name: String
    ): CharactersPageDTO

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") it: Int,
    ): CharacterDTO
}
