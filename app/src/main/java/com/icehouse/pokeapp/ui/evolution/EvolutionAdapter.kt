package com.icehouse.pokeapp.ui.evolution

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.data.model.evolutionListModel.EvolutionData
import com.icehouse.pokeapp.databinding.EvolutionListItemBinding

class EvolutionAdapter(private val evolutionData: List<EvolutionData>) :
    RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val binding = EvolutionListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EvolutionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        val data = evolutionData[position]
        holder.binding.evolutionStartName.text = data.evolution_from_name.capitalize()
        holder.binding.evolutionEndName.text = data.evolution_to_name.capitalize()
        holder.binding.evolutionLevel.text = "L x ${data.level}"

        val evolutionStartId = ((data.evolution_from_url.replace("https://","")).split("/")[4])
        val startImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${evolutionStartId}.png"

        val evolutionEndId = ((data.evolution_to_url.replace("https://","")).split("/")[4])
        val endImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${evolutionEndId}.png"

        Glide.with(holder.binding.root)
            .load(startImageUrl)
            .placeholder(R.drawable.ic_app_logo)
            .into(holder.binding.pokemonEvolutionStartImage)

        Glide.with(holder.binding.root)
            .load(endImageUrl)
            .placeholder(R.drawable.ic_app_logo)
            .into(holder.binding.pokemonEvolutionEndImage)
    }

    override fun getItemCount() = evolutionData.size

    class EvolutionViewHolder(val binding: EvolutionListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
