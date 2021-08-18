package com.icehouse.pokeapp.data.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData

@Dao
interface PokemonListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(data: List<PokemonData>?)

    @Query("SELECT * FROM PokemonData")
    fun getPokemonList(): DataSource.Factory<Int, PokemonData>

    @Query("SELECT COUNT(id) FROM PokemonData")
    suspend fun getItemCount(): Int
}