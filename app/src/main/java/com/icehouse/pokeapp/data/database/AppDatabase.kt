package com.icehouse.pokeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.icehouse.pokeapp.data.database.dao.EvolutionDao
import com.icehouse.pokeapp.data.database.dao.PokemonDetailsDao
import com.icehouse.pokeapp.data.database.dao.PokemonListDao
import com.icehouse.pokeapp.data.database.dao.PokemonSpeciesDao
import com.icehouse.pokeapp.data.database.typeConverter.EvolutionTypeConverter
import com.icehouse.pokeapp.data.database.typeConverter.PokemonDetailsTypeConverter
import com.icehouse.pokeapp.data.database.typeConverter.PokemonSpeciesTypeConverter
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionEntity
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse
import com.icehouse.pokeapp.data.model.statsResponse.PokemonDetails

@Database(
    entities = [PokemonData::class, EvolutionEntity::class, PokemonSpeciesResponse::class,
               PokemonDetails::class],
    version = 1
)
@TypeConverters(EvolutionTypeConverter::class,
    PokemonSpeciesTypeConverter::class, PokemonDetailsTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPokemonListDao(): PokemonListDao
    abstract fun getEvolutionDao(): EvolutionDao
    abstract fun getSpeciesDao(): PokemonSpeciesDao
    abstract fun getPokemonDetailsDao(): PokemonDetailsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pokemon_database"
            ).fallbackToDestructiveMigration().build()
    }
}