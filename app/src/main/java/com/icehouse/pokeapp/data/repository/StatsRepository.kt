package com.icehouse.pokeapp.data.repository

import com.icehouse.pokeapp.data.database.AppDatabase
import com.icehouse.pokeapp.data.model.abilityDescriptionResponse.AbilityDescriptionResponse
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse
import com.icehouse.pokeapp.data.model.statsResponse.StatsResponse
import com.icehouse.pokeapp.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class StatsRepository(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase) {

    suspend fun getPokemonDetails(pokemonId: Int): Response<StatsResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getPokemonDetails(pokemonId)
        }
    }

    suspend fun getAbilityDescription(abilityId: Int): Response<AbilityDescriptionResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getAbilityDescription(abilityId)
        }
    }

    suspend fun getPokemonSpecies(pokemonId: Int): Response<PokemonSpeciesResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getPokemonSpecies(pokemonId)
        }
    }
}