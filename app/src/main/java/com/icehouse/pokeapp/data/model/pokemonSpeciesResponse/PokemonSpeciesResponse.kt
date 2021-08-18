package com.icehouse.pokeapp.data.model.pokemonSpeciesResponse

import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup

data class PokemonSpeciesResponse(
    val evolution_chain: EvolutionChain,
    val flavor_text_entries: List<FlavorTextEntry>,
    val egg_groups: List<EggGroup>,
    val capture_rate: Int,
    val generation: Generation,
    val habitat: Habitat)