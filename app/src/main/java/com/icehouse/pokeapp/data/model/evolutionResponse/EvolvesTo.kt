package com.icehouse.pokeapp.data.model.evolutionResponse

data class EvolvesTo(
    val evolution_details: List<EvolutionDetails>,
    val evolves_to: List<EvolvesTo>,
    val species: Species
)