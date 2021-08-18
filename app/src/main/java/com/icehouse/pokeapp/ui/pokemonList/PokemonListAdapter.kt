package com.icehouse.pokeapp.ui.pokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.pokemonListResponse.PokemonData
import com.icehouse.pokeapp.databinding.PokemonListLayoutBinding

class PokemonListAdapter(val callback: IPokemonList) :
    PagedListAdapter<PokemonData, PokemonListAdapter.PokemonListViewHolder>(PokemonListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding = PokemonListLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.binding.pokemonName.text = it.name.capitalize()
            val urlString = it.url.replace("https://","")
            val pokemonId = urlString.split("/")[4]
            holder.binding.pokemonNumber.text = "#${String.format("%03d", pokemonId.toInt())}"
            val pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png"
            Glide.with(holder.binding.root)
                .load(pokemonImageUrl)
                .centerCrop()
                .error(R.drawable.ic_app_logo)
                .into(holder.binding.pokemonImage)
            holder.binding.root.setOnClickListener { view ->
                callback.onPokemonSelected(pokemonId.toInt())
            }
        }
    }

    class PokemonListViewHolder(val binding: PokemonListLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val PokemonListDiffUtil = object : DiffUtil.ItemCallback<PokemonData>() {
            override fun areItemsTheSame(oldItem: PokemonData, newItem: PokemonData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PokemonData, newItem: PokemonData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
