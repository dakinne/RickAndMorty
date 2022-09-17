package com.noox.rickandmorty.util

import androidx.paging.PagingSource
import com.noox.rickandmorty.character.data.CharacterDTO
import com.noox.rickandmorty.character.data.CharactersPageDTO
import com.noox.rickandmorty.character.data.InfoDTO
import com.noox.rickandmorty.character.data.LocationDTO
import com.noox.rickandmorty.character.data.OriginDTO
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.domain.model.Location
import com.noox.rickandmorty.character.domain.model.Origin
import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.core.model.Status

class Constants {

    val jsonOfEmptyCharactersPage1 by lazy {
        """
{
	"info": {
		"count": 826,
		"pages": 42,
		"next": null,
		"prev": null
	},
	"results": [{
			"id": 1
		}
	]
}
        """.trimIndent()
    }

    val emptyCharactersPageDTO1 by lazy {
        CharactersPageDTO(
            info = InfoDTO(
                pages = 42,
                next = null,
                prev = null
            ),
            characters = listOf(
                emptyCharacterDTO
            )
        )
    }

    val emptyCharacterDTO by lazy {
        CharacterDTO(
            id = 1,
            name = null,
            status = null,
            species = null,
            type = null,
            gender = null,
            image = null,
            origin = null,
            location = null
        )
    }

    val jsonOfCharactersPage1 by lazy {
        """
{
	"info": {
		"count": 826,
		"pages": 42,
		"next": "https://rickandmortyapi.com/api/character?page=2",
		"prev": null
	},
	"results": [{
			"id": 1,
			"name": "Rick Sanchez",
			"status": "Alive",
			"species": "Human",
			"type": "",
			"gender": "Male",
			"origin": {
				"name": "Earth (C-137)",
				"url": "https://rickandmortyapi.com/api/location/1"
			},
			"location": {
				"name": "Citadel of Ricks",
				"url": "https://rickandmortyapi.com/api/location/3"
			},
			"image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
			"url": "https://rickandmortyapi.com/api/character/1",
			"created": "2017-11-04T18:48:46.250Z"
		},
		{
			"id": 2,
			"name": "Morty Smith",
			"status": "Alive",
			"species": "Human",
			"type": "",
			"gender": "Male",
			"origin": {
				"name": "unknown",
				"url": ""
			},
			"location": {
				"name": "Citadel of Ricks",
				"url": "https://rickandmortyapi.com/api/location/3"
			},
			"image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
			"url": "https://rickandmortyapi.com/api/character/2",
			"created": "2017-11-04T18:50:21.651Z"
		},
		{
			"id": 3,
			"name": "Summer Smith",
			"status": "Alive",
			"species": "Human",
			"type": "",
			"gender": "Female",
			"origin": {
				"name": "Earth (Replacement Dimension)",
				"url": "https://rickandmortyapi.com/api/location/20"
			},
			"location": {
				"name": "Earth (Replacement Dimension)",
				"url": "https://rickandmortyapi.com/api/location/20"
			},
			"image": "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
			"url": "https://rickandmortyapi.com/api/character/3",
			"created": "2017-11-04T19:09:56.428Z"
		}
	]
}
        """.trimIndent()
    }

    val charactersPageDTO1 by lazy {
        CharactersPageDTO(
            info = InfoDTO(
                pages = 42,
                next = "https://rickandmortyapi.com/api/character?page=2",
                prev = null
            ),
            characters = listOf(
                characterDTO1,
                characterDTO2,
                characterDTO3
            )
        )
    }

    val jsonOfCharacter1 by lazy {
        """
{
    "id": 1,
    "name": "Rick Sanchez",
    "status": "Alive",
    "species": "Human",
    "type": "",
    "gender": "Male",
    "origin": {
        "name": "Earth (C-137)",
        "url": "https://rickandmortyapi.com/api/location/1"
    },
    "location": {
        "name": "Citadel of Ricks",
        "url": "https://rickandmortyapi.com/api/location/3"
    },
    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    "url": "https://rickandmortyapi.com/api/character/1",
    "created": "2017-11-04T18:48:46.250Z"
}
        """.trimIndent()
    }

    val characterDTO1 by lazy {
        CharacterDTO(
            id = 1,
            name = "Rick Sanchez",
            status = Status.Alive,
            species = "Human",
            type = "",
            gender = Gender.Male,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            origin = OriginDTO(
                name = "Earth (C-137)",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            location = LocationDTO(
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3"
            )
        )
    }

    val emptyCharacter by lazy {
        Character(
            id = 1,
            name = "",
            status = Status.unknown,
            species = "",
            type = "",
            gender = Gender.unknown,
            image = "",
            origin = null,
            location = null
        )
    }

    val character1 by lazy {
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = Status.Alive,
            species = "Human",
            type = "",
            gender = Gender.Male,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            origin = Origin(
                name = "Earth (C-137)",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            location = Location(
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3"
            )
        )
    }

    val characterDTO2 by lazy {
        CharacterDTO(
            id = 2,
            name = "Morty Smith",
            status = Status.Alive,
            species = "Human",
            type = "",
            gender = Gender.Male,
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            origin = OriginDTO(
                name = "unknown",
                url = ""
            ),
            location = LocationDTO(
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3"
            )
        )
    }

    val characterDTO3 by lazy {
        CharacterDTO(
            id = 3,
            name = "Summer Smith",
            status = Status.Alive,
            species = "Human",
            type = "",
            gender = Gender.Female,
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            origin = OriginDTO(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            location = LocationDTO(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            )
        )
    }

    val loadResultPage1 by lazy {
        PagingSource.LoadResult.Page(
            data = listOf(characterDTO1, characterDTO2, characterDTO3),
            prevKey = null,
            nextKey = 2
        )
    }
}
