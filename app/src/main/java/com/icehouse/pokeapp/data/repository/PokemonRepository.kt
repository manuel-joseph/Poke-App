package com.icehouse.pokeapp.data.repository

import com.icehouse.pokeapp.data.database.AppDatabase
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonListResponse
import com.icehouse.pokeapp.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonRepository(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase) {

    suspend fun getPokemonList(offset: Int): Response<PokemonListResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.getPokemonList(offset)
        }
    }

    suspend fun insertPokemonListIntoLocal(data: List<PokemonData>) {
        withContext(Dispatchers.IO) {
            appDatabase.getPokemonListDao().insertPokemonList(data)
        }
    }

    /**
     * This function will return the number of Pokemon's synced into the local db
     */
    suspend fun getRowCountFromLocal() = appDatabase.getPokemonListDao().getItemCount()

    fun getPokemonListFromLocal() = appDatabase.getPokemonListDao().getPokemonList()
}