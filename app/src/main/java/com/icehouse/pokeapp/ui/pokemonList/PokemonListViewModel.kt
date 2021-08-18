package com.icehouse.pokeapp.ui.pokemonList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData
import com.icehouse.pokeapp.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    lateinit var context: Context
    lateinit var pokemonList: LiveData<PagedList<PokemonData>>
    var offset = 0
    var hasMorePages = true

    fun setupPagination() {
        /**
         * Check if there are any Pokemon data available in the local db.
         * If so, set offset accordingly
         */
        getOffsetCount()

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()

        pokemonList = LivePagedListBuilder(
            pokemonRepository.getPokemonListFromLocal(),
            config).setBoundaryCallback(object : PagedList.BoundaryCallback<PokemonData>() {

            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                /**
                 * No data available in the local db. Get data from server.
                 */
                getPokemonListFromServer(offset)
            }

            override fun onItemAtEndLoaded(itemAtEnd: PokemonData) {
                super.onItemAtEndLoaded(itemAtEnd)
                if(hasMorePages) {
                    getPokemonListFromServer(offset)
                }
            }
        }).build()
    }

    private fun getPokemonListFromServer(page: Int) {
        viewModelScope.launch {
            try {
                val response = pokemonRepository.getPokemonList(page)
                when (response.code()) {
                    200 -> {
                        val data = response.body()?.results
                        if(!data.isNullOrEmpty()) {
                            hasMorePages = true
                            pokemonRepository.insertPokemonListIntoLocal(data)
                            offset += 20
                        } else {
                            hasMorePages = false
                        }
                    }

                    else -> println("### Something went wrong..")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getOffsetCount() {
        viewModelScope.launch {
            offset = pokemonRepository.getRowCountFromLocal()
        }
    }
}