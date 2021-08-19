package com.icehouse.pokeapp.data.model.statsResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDetails(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val stats: List<Stat>,
    val types: List<Type>,
    var abilities: List<Ability>,
    val sprites: Sprites
)