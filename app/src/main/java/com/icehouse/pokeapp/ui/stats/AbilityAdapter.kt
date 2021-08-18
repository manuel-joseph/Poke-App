package com.icehouse.pokeapp.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icehouse.pokeapp.data.model.statsResponse.Ability
import com.icehouse.pokeapp.databinding.AbilitiesListItemBinding

class AbilityAdapter(private val abilityList: List<Ability>) :
    RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val binding = AbilitiesListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AbilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.binding.abilityTitle.text = abilityList[position].ability.name.capitalize()
        holder.binding.abilityDescription.text = abilityList[position].ability.description
    }

    override fun getItemCount() = abilityList.size

    class AbilityViewHolder(val binding: AbilitiesListItemBinding): RecyclerView.ViewHolder(binding.root)
}
