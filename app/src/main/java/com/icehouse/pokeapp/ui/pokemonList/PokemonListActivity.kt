package com.icehouse.pokeapp.ui.pokemonList

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData
import com.icehouse.pokeapp.databinding.ActivityPokemonListBinding
import com.icehouse.pokeapp.ui.base.BaseActivity
import com.icehouse.pokeapp.ui.pokemonDetails.PokemonDetailsActivity

class PokemonListActivity : BaseActivity(), IPokemonList {
    private lateinit var binding: ActivityPokemonListBinding
    private lateinit var homeViewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        //set toolbar title
        binding.toolbarLayout.toolbarTitle.text = getString(R.string.pokemon_title)

        //init view model
        homeViewModel = ViewModelProvider(this, factory).get(PokemonListViewModel::class.java)

        //setup recycler view
        binding.pokemonRecycler.layoutManager = LinearLayoutManager(this)
        val pokemonListAdapter = PokemonListAdapter(this)
        binding.pokemonRecycler.adapter = pokemonListAdapter
        homeViewModel.setupPagination()

        homeViewModel.pokemonList.observe(this, {
            pokemonListAdapter.submitList(it)
        })
    }

    override fun onPokemonSelected(pokemonId: Int) {
        Intent(this, PokemonDetailsActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(getString(R.string.pokemon_id), pokemonId)
            startActivity(this)
        }
    }
}