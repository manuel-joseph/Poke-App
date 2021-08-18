package com.icehouse.pokeapp.data.database.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionData

class EvolutionTypeConverter {
    @TypeConverter
    fun fromEvolutionData(data: List<EvolutionData>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toEvolutionData(data: String): List<EvolutionData> {
        val listType = object : TypeToken<ArrayList<EvolutionData>>() {}.getType()
        return Gson().fromJson(data, listType)
    }
}