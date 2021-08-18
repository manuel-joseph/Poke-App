package com.icehouse.pokeapp.ui.pokemonDetails

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.icehouse.pokeapp.R
import com.icehouse.pokeapp.databinding.ActivityPokemonDetailsBinding
import com.icehouse.pokeapp.ui.base.BaseActivity

class PokemonDetailsActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    lateinit var binding: ActivityPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val pokemonId = intent.getIntExtra(getString(R.string.pokemon_id),0)
        binding.pokemonDetailsLayout.pokemonViewPager.adapter =
            ViewPagerAdapter(supportFragmentManager,pokemonId,this)
        binding.pokemonDetailsLayout.pokemonViewPager.currentItem = 0

        binding.pokemonDetailsLayout.pokemonStats.setOnClickListener {
            binding.pokemonDetailsLayout.pokemonViewPager.currentItem = 0
        }

        binding.pokemonDetailsLayout.pokemonEvolution.setOnClickListener {
            binding.pokemonDetailsLayout.pokemonViewPager.currentItem = 1
        }

        binding.pokemonDetailsLayout.pokemonViewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                binding.pokemonDetailsLayout.pokemonStats.chipBackgroundColor =
                    getColorStateList(R.color.blue)
                binding.pokemonDetailsLayout.pokemonStats.setTextColor(getColorStateList(R.color.white))

                binding.pokemonDetailsLayout.pokemonEvolution.chipBackgroundColor =
                    getColorStateList(R.color.white)
                binding.pokemonDetailsLayout.pokemonEvolution.setTextColor(getColorStateList(R.color.blue))
            }

            1 -> {
                binding.pokemonDetailsLayout.pokemonStats.chipBackgroundColor =
                    getColorStateList(R.color.white)
                binding.pokemonDetailsLayout.pokemonStats.setTextColor(getColorStateList(R.color.blue))

                binding.pokemonDetailsLayout.pokemonEvolution.chipBackgroundColor =
                    getColorStateList(R.color.blue)
                binding.pokemonDetailsLayout.pokemonEvolution.setTextColor(getColorStateList(R.color.white))
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}