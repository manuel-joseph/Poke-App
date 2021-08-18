package com.icehouse.pokeapp.ui.base

import android.app.Application
import com.icehouse.pokeapp.data.database.AppDatabase
import com.icehouse.pokeapp.data.network.ApiServices
import com.icehouse.pokeapp.data.repository.EvolutionRepository
import com.icehouse.pokeapp.data.repository.PokemonRepository
import com.icehouse.pokeapp.data.repository.StatsRepository
import com.icehouse.pokeapp.utils.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class PokeApp: Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(androidXModule(this@PokeApp))

        bind() from singleton { ApiServices(instance()).create() }
        bind() from singleton { AppDatabase.invoke(instance()) }
        bind() from singleton { PokemonRepository(instance(), instance()) }
        bind() from singleton { EvolutionRepository(instance(), instance()) }
        bind() from singleton { StatsRepository(instance(), instance()) }
        bind() from singleton { ViewModelFactory(instance()) }
    }
}