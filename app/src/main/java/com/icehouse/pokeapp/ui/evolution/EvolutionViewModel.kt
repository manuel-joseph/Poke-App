package com.icehouse.pokeapp.ui.evolution

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionData
import com.icehouse.pokeapp.data.repository.EvolutionRepository
import com.icehouse.pokeapp.utils.toast
import kotlinx.coroutines.launch

class EvolutionViewModel(private val evolutionRepository: EvolutionRepository): ViewModel() {
    var pokemonId: Int? = null
    lateinit var context: Context

    private val evolutionDataList = MutableLiveData<List<EvolutionData>>()
    val evolutionData: LiveData<List<EvolutionData>>
        get() = evolutionDataList

    private fun getEvolution(evolutionChainId: Int) {
        viewModelScope.launch {
            try {
                val response = evolutionRepository.getEvolution(evolutionChainId)
                when (response.code()) {
                    200 -> {
                        response.body()?.chain?.let {
                            val evolutionList = ArrayList<EvolutionData>()
                            var evolvesTo = it.evolves_to
                            evolutionList.add(EvolutionData(it.species.name,it.species.url,
                                evolvesTo[0].species.name,evolvesTo[0].species.url,
                                evolvesTo[0].evolution_details[0].min_level))
                            while (true) {
                                if(evolvesTo.isNotEmpty() && evolvesTo[0].evolves_to.isNotEmpty()) {
                                    evolutionList.add(EvolutionData(evolvesTo[0].species.name,evolvesTo[0].species.url,
                                        evolvesTo[0].evolves_to[0].species.name,evolvesTo[0].evolves_to[0].species.url,
                                        evolvesTo[0].evolves_to[0].evolution_details[0].min_level))
                                    evolvesTo = evolvesTo[0].evolves_to
                                    continue
                                }
                                break
                            }
                            evolutionDataList.value = evolutionList
                            evolutionRepository.insertEvolutionDataLocal(pokemonId!!,evolutionList)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Check if the data for selected Species is available in the local db, if so load data from there.
     * If not available try get data from the server..
     */
    fun getEvolutionFromLocal() {
        viewModelScope.launch {
            val data = evolutionRepository.getEvolutionDataLocal(pokemonId!!)
            if(data == null || data.evolutionData.isNullOrEmpty()) {
                getPokemonSpecies()
            } else {
                evolutionDataList.value = data.evolutionData
            }
        }
    }

    /**
     * From "pokemon-species/{pokemonId}" API we'll get the evolution chain URL for the
     * pokemon the user has selected
     */
    private fun getPokemonSpecies() {
        if(pokemonId == null) {
            return
        }

        viewModelScope.launch {
            try {
                val response = evolutionRepository.getPokemonSpecies(pokemonId!!)
                when(response.code()) {
                    200 -> {
                        val evolutionUrl = response.body()?.evolution_chain?.url
                        if(!evolutionUrl.isNullOrEmpty()) {
                            val url = evolutionUrl.replace("https://","")
                            val evolutionChainId = (url.split("/")[4]).toInt()
                            getEvolution(evolutionChainId)
                        } else {
                            context.toast(context.getString(R.string.error_message))
                        }
                    }

                    404 -> context.toast(context.getString(R.string.server_not_reachable))

                    else -> context.toast(context.getString(R.string.error_message))
                }
            } catch (e: Exception) {
                when (e.message) {
                    context.getString(R.string.internet_error_title) -> {
                        context.toast(context.getString(R.string.internet_not_available))
                    }

                    else -> context.toast(context.getString(R.string.error_message))
                }
                e.printStackTrace()
            }
        }
    }
}