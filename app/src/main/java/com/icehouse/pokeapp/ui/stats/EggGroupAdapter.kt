package com.icehouse.pokeapp.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icehouse.pokeapp.data.model.evolutionResponse.EggGroup
import com.icehouse.pokeapp.databinding.EggGroupListItemBinding

class EggGroupAdapter(private val eggGroupList: List<EggGroup>) :
    RecyclerView.Adapter<EggGroupAdapter.EggGroupViewHolder>() {

    class EggGroupViewHolder(val binding: EggGroupListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EggGroupViewHolder {
        val binding = EggGroupListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return EggGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EggGroupViewHolder, position: Int) {
        holder.binding.eggGroupName.text = eggGroupList[position].name.capitalize()
    }

    override fun getItemCount() = eggGroupList.size
}
