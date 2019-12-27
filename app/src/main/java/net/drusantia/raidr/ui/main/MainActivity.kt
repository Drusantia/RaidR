package net.drusantia.raidr.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val vm by viewModel<MainViewModel>()
    private val fenrohasObserver = Observer<PlayerCharacter> { Timber.d("$it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        vm.fenrohas.observe(this, fenrohasObserver)
        vm.load()
    }
}