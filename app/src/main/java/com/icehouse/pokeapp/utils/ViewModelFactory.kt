package com.icehouse.pokeapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.icehouse.pokeapp.data.repository.EvolutionRepository
import com.icehouse.pokeapp.data.repository.PokemonRepository
import com.icehouse.pokeapp.data.repository.StatsRepository
import com.icehouse.pokeapp.ui.evolution.EvolutionViewModel
import com.icehouse.pokeapp.ui.pokemonList.PokemonListViewModel
import com.icehouse.pokeapp.ui.stats.StatsViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ViewModelFactory(context: Context): ViewModelProvider.NewInstanceFactory(), KodeinAware {

    override val kodein by kodein(context)
    private val pokemonRepository by instance<PokemonRepository>()
    private val evolutionRepository by instance<EvolutionRepository>()
    private val statsRepository by instance<StatsRepository>()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(PokemonListViewModel::class.java) -> {
                return PokemonListViewModel(pokemonRepository) as T
            }

            modelClass.isAssignableFrom(EvolutionViewModel::class.java) -> {
                return EvolutionViewModel(evolutionRepository) as T
            }

            modelClass.isAssignableFrom(StatsViewModel::class.java) -> {
                return StatsViewModel(statsRepository) as T
            }

            else -> throw IllegalArgumentException("Invalid view model")
        }
    }
}