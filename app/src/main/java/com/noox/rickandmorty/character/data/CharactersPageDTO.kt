package com.noox.rickandmorty.character.data

import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.core.model.Status
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersPageDTO(
    @Json(name = "info") val info: InfoDTO,
    @Json(name = "results") val characters: List<CharacterDTO>
)

@JsonClass(generateAdapter = true)
data class InfoDTO(
    @Json(name = "pages") val pages: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "prev") val prev: String?
)

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String?,
    @Json(name = "status") val status: Status?,
    @Json(name = "species") val species: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "gender") val gender: Gender?,
    @Json(name = "image") val image: String?,

    @Json(name = "origin") val origin: OriginDTO?,
    @Json(name = "location") val location: LocationDTO?
)

@JsonClass(generateAdapter = true)
data class OriginDTO(
    @Json(name = "name") val name: String?,
    @Json(name = "url") val url: String?
)

@JsonClass(generateAdapter = true)
data class LocationDTO(
    @Json(name = "name") val name: String?,
    @Json(name = "url") val url: String?
)
