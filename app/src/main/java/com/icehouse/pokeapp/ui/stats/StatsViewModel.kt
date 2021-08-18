package com.icehouse.pokeapp.ui.stats

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup
import com.icehouse.pokeapp.data.model.statsResponse.Ability
import com.icehouse.pokeapp.data.model.statsResponse.Stat
import com.icehouse.pokeapp.data.model.statsResponse.StatData
import com.icehouse.pokeapp.data.repository.StatsRepository
import com.icehouse.pokeapp.utils.toast
import kotlinx.coroutines.launch
import java.lang.Exception

class StatsViewModel(private val statsRepository: StatsRepository) : ViewModel() {
    lateinit var context: Context
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

    fun getPokemonDetails() {
        viewModelScope.launch {
            try {
                val response = statsRepository.getPokemonDetails(pokemonId!!)

                when (response.code()) {
                    200 -> {
                        val data = response.body()?.stats
                        if (!data.isNullOrEmpty()) {
                            statsLiveData.value = data
                        }

                        val abilityList = response.body()?.abilities
                        if(!abilityList.isNullOrEmpty()) {
                            getAbilityDescription(abilityList)
                        }

                        pokemonImageLiveData.value =
                            response.body()?.sprites?.other?.`official-artwork`?.front_default
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

    private fun getAbilityDescription(abilityList: List<Ability>) {
        viewModelScope.launch {
            for (data in abilityList) {
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
                                abilityLiveData.value = abilityList
                            }
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
    }

    fun getSpeciesDetails() {
        viewModelScope.launch {
            try {
                val response = statsRepository.getPokemonSpecies(pokemonId!!)

                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        val eggGroupList = data?.egg_groups
                        if(!eggGroupList.isNullOrEmpty()) {
                            eggGroupLiveData.value = eggGroupList
                        }
                        captureRateLiveData.value = "${data?.capture_rate?.toString()}%"
                        habitatLiveData.value = data?.habitat?.name
                        generationLiveData.value = data?.generation?.name
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
}