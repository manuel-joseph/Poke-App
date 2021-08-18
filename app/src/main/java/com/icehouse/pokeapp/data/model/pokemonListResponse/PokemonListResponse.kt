package com.icehouse.pokeapp.data.model.pokemonListResponse

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonData>
)