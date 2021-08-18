package com.icehouse.pokeapp.data.model.pokemonListResponse

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class PokemonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val url: String
)