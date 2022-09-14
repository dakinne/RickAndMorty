package com.noox.rickandmorty.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.noox.rickandmorty.databinding.ItemPagingFooterBinding

class PagingFooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    tryAgain: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.tryAgainButton.setOnClickListener { tryAgain() }
    }

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorLayout.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, tryAgain: () -> Unit): PagingFooterViewHolder {
            val binding = ItemPagingFooterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return PagingFooterViewHolder(binding) { tryAgain() }
        }
    }
}
