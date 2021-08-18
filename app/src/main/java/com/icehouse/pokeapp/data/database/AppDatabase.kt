package com.icehouse.pokeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.icehouse.pokeapp.data.database.dao.EvolutionDao
import com.icehouse.pokeapp.data.database.dao.PokemonListDao
import com.icehouse.pokeapp.data.database.typeConverter.EvolutionTypeConverter
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionData
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionEntity
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData

@Database(entities = [PokemonData::class, EvolutionEntity::class],version = 2)
@TypeConverters(EvolutionTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPokemonListDao(): PokemonListDao
    abstract fun getEvolutionDao(): EvolutionDao

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