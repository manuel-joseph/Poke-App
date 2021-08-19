package com.icehouse.pokeapp.data.model.pokemonSpeciesResponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup

@Entity(tableName = "PokemonSpecies")
data class PokemonSpeciesResponse(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val evolution_chain: EvolutionChain,
    val flavor_text_entries: List<FlavorTextEntry>,
    val egg_groups: List<EggGroup>,
    val capture_rate: Int,
    val generation: Generation,
    val habitat: Habitat,
    val name: String)