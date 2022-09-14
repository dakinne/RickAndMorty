package com.noox.rickandmorty.character.domain.model

import android.os.Parcelable
import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.core.model.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val type: String,
    val image: String,
    val status: Status,
    val gender: Gender,
    val species: String,

    val origin: Origin?,
    val location: Location?
) : Parcelable {

    val hasKnownOrigin = origin?.name != "unknown"
    val hasKnownLocation = location?.name != "unknown"
}

@Parcelize
data class Origin(val name: String, val url: String) : Parcelable

@Parcelize
data class Location(val name: String, val url: String) : Parcelable
