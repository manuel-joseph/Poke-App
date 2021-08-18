package com.icehouse.pokeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionEntity

@Dao
interface EvolutionDao {
    @Query("SELECT * FROM EvolutionEntity WHERE id =:id")
    suspend fun getEvolutionData(id: Int): EvolutionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvolutionData(data: EvolutionEntity)
}