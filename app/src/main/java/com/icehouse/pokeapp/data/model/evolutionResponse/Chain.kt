package com.icehouse.pokeapp.data.model.evolutionResponse

data class Chain(
    val evolution_details: List<EvolutionDetails>,
    val evolves_to: List<EvolvesTo>,
    val species: Species
)