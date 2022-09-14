package com.noox.rickandmorty.core.data

data class Page<T>(
        val items: List<T> = emptyList(),
        val currentPage: Int,
        val totalPages: Int
) {
    val isEmpty = items.isEmpty()
    val isFirstPage = currentPage == 1
    val hasMorePages = currentPage + 1 < totalPages
}
