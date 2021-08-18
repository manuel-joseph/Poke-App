package com.icehouse.pokeapp.ui.evolution

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.databinding.FragmentEvolutionBinding
import com.icehouse.pokeapp.utils.ViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class EvolutionFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val factory by instance<ViewModelFactory>()
    private lateinit var evolutionViewModel: EvolutionViewModel
    private lateinit var binding: FragmentEvolutionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        evolutionViewModel = ViewModelProvider(requireActivity(),factory).get(EvolutionViewModel::class.java)
        binding = FragmentEvolutionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        evolutionViewModel.context = requireContext()
        arguments?.let {
            evolutionViewModel.pokemonId = it.getInt(getString(R.string.pokemon_id))
        }
        evolutionViewModel.evolutionData.observe(requireActivity(), {
            binding.evolutionRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.evolutionRecycler.adapter = EvolutionAdapter(it)
        })

        evolutionViewModel.getEvolutionFromLocal()
    }
}