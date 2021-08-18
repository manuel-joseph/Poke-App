package com.icehouse.pokeapp.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icehouse.pokeapp.data.model.statsResponse.Stat
import com.icehouse.pokeapp.databinding.StatsListItemBinding

class StatsAdapter(private val statList: List<Stat>) : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val binding = StatsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val statData = statList[position]
        holder.binding.statName.text = when (statData.stat.name) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> ""
        }
        holder.binding.statProgress.progress = statData.base_stat
        holder.binding.statEffort.text = String.format("%03d",statData.effort)
    }

    override fun getItemCount() = statList.size

    class StatsViewHolder(val binding: StatsListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
