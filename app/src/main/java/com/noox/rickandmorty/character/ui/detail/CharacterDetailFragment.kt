package com.noox.rickandmorty.character.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.noox.rickandmorty.R
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.ui.detail.CharacterDetailViewModel.UiState
import com.noox.rickandmorty.core.extensions.setOnSafeClickListener
import com.noox.rickandmorty.core.model.Gender
import com.noox.rickandmorty.core.model.Status
import com.noox.rickandmorty.databinding.FragmentCharacterDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {

    companion object {
        private const val ARG_CHARACTER_ID = "characterId"

        fun bundle(characterId: Int) = bundleOf(ARG_CHARACTER_ID to characterId)
    }

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CharacterDetailViewModel> { parametersOf(characterId) }

    private val characterId: Int by lazy {
        requireArguments().getInt(ARG_CHARACTER_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.setOnSafeClickListener { findNavController().navigateUp() }

        binding.tryAgainButton.setOnSafeClickListener { viewModel.loadCharacter() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { perform(it) }
            }
        }
    }

    private fun perform(uiState: UiState) {
        with(binding) {
            loading.isVisible = uiState is UiState.Loading
            content.isVisible = uiState is UiState.Success
            error.isVisible = uiState is UiState.Error
        }

        if (uiState is UiState.Success) {
            showCharacterData(uiState.character)
        }
    }

    private fun showCharacterData(character: Character) {
        with(binding) {

            image.load(character.image) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            }

            name.text = character.name

            renderSpecies(character)

            val pronoun = getPronoun(character)
            renderStatus(character, pronoun)
            renderType(character, pronoun)

            renderOrigin(character)
            renderLocation(character)

            location.text = character.location?.name ?: ""
        }
    }

    private fun renderSpecies(character: Character) {
        val species = if (character.species == "unknown") "" else character.species
        val gender = if (character.gender == Gender.unknown) "" else character.gender

        binding.species.text = getString(R.string.species_format, species, gender).trim()
    }

    private fun renderStatus(character: Character, pronoun: String) {
        binding.status.text = when (character.status) {
            Status.Alive -> getString(R.string.status_format, pronoun, getString(R.string.alive))
            Status.Dead -> getString(R.string.status_format, pronoun, getString(R.string.dead))
            Status.unknown -> getString(R.string.status_unknown_format, pronoun.lowercase())
        }
    }

    private fun renderType(character: Character, pronoun: String) {
        if (character.type.isBlank()) {
            binding.type.isVisible = false
        } else {
            val type = character.type.replaceFirstChar { it.lowercase() }
            binding.type.text = getString(R.string.type_format, pronoun, type)
        }
    }

    private fun renderOrigin(character: Character) {
        binding.origin.text = if (character.hasKnownOrigin) {
            character.origin?.name
        } else {
            getString(R.string.origin_unknown)
        }
    }

    private fun renderLocation(character: Character) {
        binding.location.text = if (character.hasKnownLocation) {
            character.location?.name
        } else {
            getString(R.string.location_unknown)
        }
    }

    private fun getPronoun(character: Character) = when (character.gender) {
        Gender.Male -> getString(R.string.male_pronoun)
        Gender.Female -> getString(R.string.female_pronoun)
        Gender.Genderless -> getString(R.string.unknown_pronoun)
        Gender.unknown -> getString(R.string.unknown_pronoun)
    }

}
