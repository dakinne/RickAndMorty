package com.noox.rickandmorty.character.data

import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.domain.model.Location
import com.noox.rickandmorty.character.domain.model.Origin
import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.core.model.Status

class CharactersMapper {

    fun mapToModel(dto: CharacterDTO) = Character(
        id = dto.id,
        name = dto.name ?: "",
        type = dto.type ?: "",
        image = dto.image ?: "",
        status = dto.status ?: Status.unknown,
        gender = dto.gender ?: Gender.unknown,
        species = dto.species ?: "",

        origin = dto.origin?.let { mapToModel(it) },
        location = dto.location?.let { mapToModel(it) }
    )

    private fun mapToModel(dto: OriginDTO) = Origin(
        name = dto.name ?: "",
        url = dto.url ?: ""
    )

    private fun mapToModel(dto: LocationDTO) = Location(
        name = dto.name ?: "",
        url = dto.url ?: ""
    )
}
