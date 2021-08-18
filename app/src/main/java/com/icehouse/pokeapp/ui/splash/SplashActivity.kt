package com.icehouse.pokeapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.icehouse.pokeapp.databinding.ActivitySplashBinding
import com.icehouse.pokeapp.ui.base.BaseActivity
import com.icehouse.pokeapp.ui.pokemonList.PokemonListActivity

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        hideSystemUI()

        setContentView(binding.root)
        navigateToHomePage()
    }

    private fun hideSystemUI () {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, binding.splashMainContainer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Hold splash screen for 3 sec on the screen and navigate to main menu (home) page
     */
    private fun navigateToHomePage() {
        Handler(mainLooper).postDelayed({
            Intent(this@SplashActivity, PokemonListActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(this)
                finish()
            }
        },3000)
    }
}