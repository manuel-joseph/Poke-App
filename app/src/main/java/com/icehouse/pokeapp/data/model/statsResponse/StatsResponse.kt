package com.icehouse.pokeapp.data.model.statsResponse

data class StatsResponse(
    val stats: List<Stat>,
    val types: List<Type>,
    val abilities: List<Ability>,
    val sprites: Sprites
)