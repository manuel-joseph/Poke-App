package com.icehouse.pokeapp.ui.stats

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup
import com.icehouse.pokeapp.data.model.pokemonSpeciesResponse.PokemonSpeciesResponse
import com.icehouse.pokeapp.data.model.statsResponse.Ability
import com.icehouse.pokeapp.data.model.statsResponse.PokemonDetails
import com.icehouse.pokeapp.data.model.statsResponse.Stat
import com.icehouse.pokeapp.data.model.statsResponse.StatData
import com.icehouse.pokeapp.data.repository.StatsRepository
import com.icehouse.pokeapp.ui.pokemonDetails.IPokemonDetails
import com.icehouse.pokeapp.utils.toast
import kotlinx.coroutines.launch
import java.lang.Exception

class StatsViewModel(private val statsRepository: StatsRepository) : ViewModel() {
    lateinit var context: Context
    lateinit var callbackListener: IPokemonDetails
    var pokemonId: Int? = null

    private val statsLiveData = MutableLiveData<List<Stat>>()
    val statsList: LiveData<List<Stat>>
        get() = statsLiveData

    private val abilityLiveData = MutableLiveData<List<Ability>>()
    val abilityList: LiveData<List<Ability>>
        get() = abilityLiveData

    private val eggGroupLiveData = MutableLiveData<List<EggGroup>>()
    val eggGroupList: LiveData<List<EggGroup>>
        get() = eggGroupLiveData

    private val generationLiveData = MutableLiveData<String>()
    val generation: LiveData<String>
        get() = generationLiveData

    private val habitatLiveData = MutableLiveData<String>()
    val habitat: LiveData<String>
        get() = habitatLiveData

    private val captureRateLiveData = MutableLiveData<String>()
    val captureRate: LiveData<String>
        get() = captureRateLiveData

    private val pokemonImageLiveData = MutableLiveData<String>()
    val pokemonImage: LiveData<String>
        get() = pokemonImageLiveData

    fun getPokemonDetailsFromLocal() {
        viewModelScope.launch {
            val details = statsRepository.getPokemonDetailsFromLocal(pokemonId!!)
            if (details != null) {
                populatePokemonDetails(details)
            } else {
                getPokemonDetails()
            }
        }
    }

    private fun getPokemonDetails() {
        viewModelScope.launch {
            try {
                val response = statsRepository.getPokemonDetails(pokemonId!!)

                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        data?.id = pokemonId!!
                        statsRepository.insertPokemonDetails(data)
                        populatePokemonDetails(data)
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

    private fun getAbilityDescription(details: PokemonDetails) {
        viewModelScope.launch {
            for (data in details.abilities) {
                try {
                    val abilityUrl = data.ability.url.replace("https://", "")
                    val abilityId = (abilityUrl.split("/")[4]).toInt()
                    val response = statsRepository.getAbilityDescription(abilityId)

                    when (response.code()) {
                        200 -> {
                            response.body()?.effect_entries?.let {
                                for (description in it) {
                                    if (description.language.name.equals("en")) {
                                        data.ability.description = description.effect
                                        break
                                    }
                                }
                                statsRepository.insertPokemonDetails(details)
                                abilityLiveData.value = details.abilities
                            }
                        }

                        else -> context.toast(context.getString(R.string.error_message))
                    }
                } catch (e: Exception) {
                    if(!e.message.equals(context.getString(R.string.internet_error_title))) {
                        context.toast(context.getString(R.string.error_message))
                    }
                    e.printStackTrace()
                }
            }
        }
    }

    fun getSpeciesFromLocal() {
        viewModelScope.launch {
            val speciesData = statsRepository.getSpeciesFromLocal(pokemonId!!)
            if(speciesData != null) {
                populateSpeciesData(speciesData)
            } else {
                getSpeciesDetails()
            }
        }
    }
    private fun getSpeciesDetails() {
        viewModelScope.launch {
            try {
                val response = statsRepository.getPokemonSpecies(pokemonId!!)

                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        data?.id = pokemonId!!
                        statsRepository.insertSpecies(data)
                        populateSpeciesData(data)
                    }

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

    private fun populateSpeciesData(data: PokemonSpeciesResponse?) {
        val eggGroupList = data?.egg_groups
        if(!eggGroupList.isNullOrEmpty()) {
            eggGroupLiveData.value = eggGroupList
        }
        captureRateLiveData.value = "${data?.capture_rate?.toString()}%"
        habitatLiveData.value = data?.habitat?.name
        generationLiveData.value = data?.generation?.name
        data?.flavor_text_entries?.get(0)?.flavor_text?.let {
            callbackListener.loadPokemonDescription(it)
        }

        data?.name?.let {
            callbackListener.loadPokemonName(it)
        }
    }

    private fun populatePokemonDetails(data: PokemonDetails?) {
        data?.let {
            it.stats?.let {
                statsLiveData.value = it
            }

            val abilityList = it.abilities
            abilityLiveData.value = abilityList
            if(!abilityList.isNullOrEmpty()) {
                getAbilityDescription(it)
            }


            val imageUrl = it.sprites?.other?.`official-artwork`?.front_default
            pokemonImageLiveData.value = imageUrl
            imageUrl?.let {
                callbackListener.loadPokemonImage(it)
            }

            it.types?.get(0)?.type?.name?.let {
                callbackListener.loadPokemonType(it)
            }
        }
    }
}