package com.icehouse.pokeapp.data.model.evolutionListModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val evolutionData: List<EvolutionData>
)