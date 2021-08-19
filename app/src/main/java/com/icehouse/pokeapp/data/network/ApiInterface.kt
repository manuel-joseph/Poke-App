package com.icehouse.pokeapp.data.network

import com.icehouse.pokeapp.data.model.abilityDescriptionResponse.AbilityDescriptionResponse
import com.icehouse.pokeapp.data.model.evolutionResponse.EvolutionResponse
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonListResponse
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse
import com.icehouse.pokeapp.data.model.statsResponse.PokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): Response<PokemonListResponse>

    @GET("evolution-chain/{chainId}")
    suspend fun getPokemonEvolution(@Path("chainId") chainId: Int): Response<EvolutionResponse>

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonSpecies(@Path("pokemonId") pokemonId: Int): Response<PokemonSpeciesResponse>

    @GET("pokemon/{pokemonId}/")
    suspend fun getPokemonDetails(@Path("pokemonId") pokemonId: Int): Response<PokemonDetails>

    @GET("ability/{abilityId}/")
    suspend fun getAbilityDescription(@Path("abilityId") abilityId: Int): Response<AbilityDescriptionResponse>
}