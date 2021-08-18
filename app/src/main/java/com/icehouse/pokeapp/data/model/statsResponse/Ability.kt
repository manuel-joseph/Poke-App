package com.icehouse.pokeapp.data.model.statsResponse

data class Ability(
    val ability: AbilityData,
    val is_hidden: Boolean,
    val slot: Int
)