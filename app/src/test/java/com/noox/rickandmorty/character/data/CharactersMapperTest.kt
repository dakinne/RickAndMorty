package com.noox.rickandmorty.character.data

import com.noox.rickandmorty.Constants
import org.junit.Assert
import org.junit.Test

class CharactersMapperTest {

    private val mapper = CharactersMapper()

    private val constants by lazy { Constants() }

    @Test
    fun givenDTO_whenMapping_ThenReturnDomainModel() {
        val expected = constants.character1
        val input = constants.characterDTO1

        val model = mapper.mapToModel(input)

        Assert.assertEquals(expected, model)
    }

}