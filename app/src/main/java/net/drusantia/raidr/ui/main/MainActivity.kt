package net.drusantia.raidr.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val vm by viewModel<MainViewModel>()
    private val fenrohasObserver = Observer<PlayerCharacter?> { Timber.d("$it") }

    init {
        lifecycleScope.launchWhenCreated {
            val binding: ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
            binding.vm = vm
            binding.lifecycleOwner = this@MainActivity
        }
        lifecycleScope.launchWhenStarted {
            vm.fenrohas.observe(this@MainActivity, fenrohasObserver)
            Timber.i("vm.load()")
            vm.load()
        }
    }
}