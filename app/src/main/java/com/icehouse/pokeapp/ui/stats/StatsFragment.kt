package com.icehouse.pokeapp.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.databinding.FragmentStatsBinding
import com.icehouse.pokeapp.utils.ViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class StatsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory by instance<ViewModelFactory>()
    private lateinit var binding: FragmentStatsBinding
    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatsBinding.inflate(inflater,container,false)
        statsViewModel = ViewModelProvider(requireActivity(),factory).get(StatsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsViewModel.context = requireContext()
        arguments?.let {
            statsViewModel.pokemonId = it.getInt(getString(R.string.pokemon_id))
        }

        statsViewModel.statsList.observe(requireActivity(), {
            binding.statsRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.statsRecycler.adapter = StatsAdapter(it)
        })

        statsViewModel.abilityList.observe(requireActivity(), {
            binding.abilitiesRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.abilitiesRecycler.adapter = AbilityAdapter(it)
        })

        statsViewModel.eggGroupList.observe(requireActivity(), {
            binding.eggGroupRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.eggGroupRecycler.adapter = EggGroupAdapter(it)
        })

        statsViewModel.captureRate.observe(requireActivity(), {
            binding.captureRate.text = it
        })

        statsViewModel.habitat.observe(requireActivity(), {
            binding.habitat.text = it
        })

        statsViewModel.generation.observe(requireActivity(), {
            binding.generation.text = it
        })

        statsViewModel.pokemonImage.observe(requireActivity(), {
            Glide.with(this)
                .load(it)
                .into(binding.normalImage)

            Glide.with(this)
                .load(it)
                .into(binding.shinyImage)
        })
        statsViewModel.getPokemonDetails()
        statsViewModel.getSpeciesDetails()
    }
}