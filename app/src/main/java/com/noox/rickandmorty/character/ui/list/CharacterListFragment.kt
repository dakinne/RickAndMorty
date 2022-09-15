package com.noox.rickandmorty.character.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.noox.rickandmorty.R
import com.noox.rickandmorty.character.domain.model.Character
import com.noox.rickandmorty.character.ui.detail.CharacterDetailFragment
import com.noox.rickandmorty.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CharacterListViewModel>()

    private val adapter by lazy { CharacterAdapter( onItemClick = { navigateToCharacter(it) } ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.characters.adapter = adapter.withLoadStateFooter(
            footer = PagingFooterAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { combinedLoadStates ->
            binding.apply {
                progressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
            }
        }

        val searchViewItem: MenuItem = binding.toolbar.menu.findItem(R.id.search)
        val searchView: SearchView = searchViewItem.actionView as SearchView
        searchView.maxWidth  = Integer.MAX_VALUE

        searchView.apply {
            queryHint = getString(R.string.search_by_name)
            clearFocus()

            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.search(query.toString())
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.search(newText.toString())
                        return true
                    }
                }
            )
        }
    }

    private fun initListeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest { adapter.submitData(it) }
            }
        }
    }

    private fun navigateToCharacter(character: Character) {
        findNavController().navigate(
            R.id.action_nav_to_character_detail,
            CharacterDetailFragment.bundle(characterId = character.id)
        )
    }
}
