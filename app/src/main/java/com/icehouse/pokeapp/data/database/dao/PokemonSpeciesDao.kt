package com.icehouse.pokeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse

@Dao
interface PokemonSpeciesDao {
    @Query("SELECT * FROM PokemonSpecies WHERE id =:pokemonId")
    suspend fun getSpecies(pokemonId: Int): PokemonSpeciesResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonSpecies(data: PokemonSpeciesResponse?)
}