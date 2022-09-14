package com.noox.rickandmorty.character.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noox.rickandmorty.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
