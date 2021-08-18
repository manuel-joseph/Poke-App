package com.icehouse.pokeapp.ui.pokemonDetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.ui.evolution.EvolutionFragment
import com.icehouse.pokeapp.ui.stats.StatsFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val pokemonId: Int,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt(context.getString(R.string.pokemon_id),pokemonId)
        return when (position) {
            1 -> {
                EvolutionFragment().apply {
                    arguments = bundle
                }
            }

            else -> StatsFragment().apply {
                arguments = bundle
            }
        }
    }
}