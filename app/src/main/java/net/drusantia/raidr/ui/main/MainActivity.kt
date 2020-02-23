package net.drusantia.raidr.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.LiveEvent
import net.drusantia.raidr.data.model.PlayerCharacter
import net.drusantia.raidr.databinding.ActivityMainBinding
import net.drusantia.raidr.utils.extensions.showHttpErrorToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val vm by viewModel<MainViewModel>()
    private val characterObserver = Observer<PlayerCharacter?> { Timber.d("$it") }
    private val errorObserver = Observer<LiveEvent<Throwable?>> { event -> event.getAndClear()?.let { error -> showHttpErrorToast(error) } }

    init {
        lifecycleScope.launchWhenCreated {
            val binding: ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
            binding.vm = vm
            binding.lifecycleOwner = this@MainActivity
        }
        lifecycleScope.launchWhenStarted {
            vm.character.observe(this@MainActivity, characterObserver)
            vm.error.observe(this@MainActivity, errorObserver)
            Timber.i("vm.load()")
            vm.load()
        }
    }
}