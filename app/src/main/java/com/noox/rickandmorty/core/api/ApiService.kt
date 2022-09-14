package com.noox.rickandmorty.core.api

import com.noox.rickandmorty.character.data.CharactersPageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 0,
        @Query("name") name: String
    ): CharactersPageDTO
}
