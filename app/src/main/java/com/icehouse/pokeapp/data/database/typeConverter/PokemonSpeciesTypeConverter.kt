package com.icehouse.pokeapp.data.database.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.EvolutionChain
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.FlavorTextEntry
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.Generation
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.Habitat

class PokemonSpeciesTypeConverter {
    @TypeConverter
    fun fromEvolutionChain(data: EvolutionChain): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toEvolutionChain(data: String): EvolutionChain {
        val listType = object : TypeToken<EvolutionChain>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromFlavorTextEntry(data: List<FlavorTextEntry>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toFlavorTextEntry(data: String): List<FlavorTextEntry> {
        val listType = object : TypeToken<List<FlavorTextEntry>>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromEggGroup(data: List<EggGroup>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toEggGroup(data: String): List<EggGroup> {
        val listType = object : TypeToken<List<EggGroup>>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromGeneration(data: Generation): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toGeneration(data: String): Generation {
        val listType = object : TypeToken<Generation>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromHabitat(data: Habitat): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toHabitat(data: String): Habitat {
        val listType = object : TypeToken<Habitat>() {}.getType()
        return Gson().fromJson(data, listType)
    }
}