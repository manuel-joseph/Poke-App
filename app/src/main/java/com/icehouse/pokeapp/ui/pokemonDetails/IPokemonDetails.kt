package com.icehouse.pokeapp.ui.pokemonDetails

interface IPokemonDetails {
    fun loadPokemonImage(image: String)

    fun loadPokemonType(type: String)

    fun loadPokemonDescription(description: String)

    fun loadPokemonName(name: String)
}