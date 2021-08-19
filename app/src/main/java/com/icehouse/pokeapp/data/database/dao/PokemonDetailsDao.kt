package com.icehouse.pokeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icehouse.pokeapp.data.model.statsResponse.PokemonDetails

@Dao
interface PokemonDetailsDao {
    @Query("SELECT * FROM PokemonDetails WHERE id =:pokemonId")
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetails(data: PokemonDetails?)
}