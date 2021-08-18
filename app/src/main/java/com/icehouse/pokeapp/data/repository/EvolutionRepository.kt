package com.icehouse.pokeapp.data.repository

import com.icehouse.pokeapp.data.database.AppDatabase
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionData
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionEntity
import com.icehouse.pokeapp.data.model.evolutionResponse.EvolutionResponse
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse
import com.icehouse.pokeapp.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class EvolutionRepository(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase) {

    suspend fun getEvolution(pokemonId: Int): Response<EvolutionResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getPokemonEvolution(pokemonId)
        }
    }

    suspend fun getPokemonSpecies(pokemonId: Int): Response<PokemonSpeciesResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getPokemonSpecies(pokemonId)
        }
    }

    suspend fun insertEvolutionDataLocal(id: Int, data: List<EvolutionData>) {
        withContext(Dispatchers.IO) {
            val evolutionEntity = EvolutionEntity(id,data)
            appDatabase.getEvolutionDao().insertEvolutionData(evolutionEntity)
        }
    }

    suspend fun getEvolutionDataLocal(id: Int): EvolutionEntity? {
        return withContext(Dispatchers.IO) {
            appDatabase.getEvolutionDao().getEvolutionData(id)
        }
    }
}