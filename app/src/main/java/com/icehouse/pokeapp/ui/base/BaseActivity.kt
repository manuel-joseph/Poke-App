package com.icehouse.pokeapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.icehouse.pokeapp.utils.ViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

open class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    protected val factory by instance<ViewModelFactory>()
}