package com.noox.rickandmorty.character.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noox.rickandmorty.R
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.core.extensions.setOnSafeClickListener
import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onItemClick: (Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Character) -> Unit) = CharacterViewHolder(
            binding = ItemCharacterBinding.inflate( LayoutInflater.from(parent.context), parent, false),
            onItemClick = onItemClick
        )
    }

    fun bind(character: Character) {
        binding.name.text = character.name
        binding.image.load(character.image) {
            crossfade(true)
            placeholder(R.drawable.image_placeholder)
        }

        val species = if (character.species == "unknown") "" else character.species
        val gender = if (character.gender == Gender.unknown) "" else character.gender

        binding.species.text = itemView.context.getString(R.string.species_format, species, gender).trim()

        itemView.setOnSafeClickListener { onItemClick(character) }
    }

}