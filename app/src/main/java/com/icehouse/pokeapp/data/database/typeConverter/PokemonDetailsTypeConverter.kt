package com.icehouse.pokeapp.data.database.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icehouse.pokeapp.data.model.statsResponse.*

class PokemonDetailsTypeConverter {
    @TypeConverter
    fun fromStatData(data: StatData): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toStatData(data: String): StatData {
        val listType = object : TypeToken<StatData>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromStat(data: List<Stat>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toStat(data: String): List<Stat> {
        val listType = object : TypeToken<List<Stat>>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromTypeData(data: TypeData): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toTypeData(data: String): TypeData {
        val listType = object : TypeToken<TypeData>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromType(data: List<Type>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toType(data: String): List<Type> {
        val listType = object : TypeToken<List<Type>>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromAbilityData(data: AbilityData): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toAbilityData(data: String): AbilityData {
        val listType = object : TypeToken<AbilityData>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromAbility(data: List<Ability>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toAbility(data: String): List<Ability> {
        val listType = object : TypeToken<List<Ability>>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromDreamWorld(data: DreamWorld): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toDreamWorld(data: String): DreamWorld {
        val listType = object : TypeToken<DreamWorld>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromOfficialArtwork(data: OfficialArtwork): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toOfficialArtwork(data: String): OfficialArtwork {
        val listType = object : TypeToken<OfficialArtwork>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromOther(data: Other): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toOther(data: String): Other {
        val listType = object : TypeToken<Other>() {}.getType()
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromSprites(data: Sprites): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toSprites(data: String): Sprites {
        val listType = object : TypeToken<Sprites>() {}.getType()
        return Gson().fromJson(data, listType)
    }
}