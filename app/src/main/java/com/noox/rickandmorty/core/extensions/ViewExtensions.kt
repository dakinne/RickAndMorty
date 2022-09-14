package com.noox.rickandmorty.core.extensions

import android.view.View
import com.noox.rickandmorty.core.ui.SafeClickListener

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { onSafeClick(it) })
}
