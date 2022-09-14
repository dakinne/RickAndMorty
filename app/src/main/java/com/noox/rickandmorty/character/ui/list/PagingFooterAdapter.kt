package com.noox.rickandmorty.character.ui.list

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.noox.rickandmorty.core.ui.PagingFooterViewHolder

class PagingFooterAdapter(
    private val tryAgain: () -> Unit
) : LoadStateAdapter<PagingFooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingFooterViewHolder {
        return PagingFooterViewHolder.create(parent) { tryAgain() }
    }

    override fun onBindViewHolder(holder: PagingFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
