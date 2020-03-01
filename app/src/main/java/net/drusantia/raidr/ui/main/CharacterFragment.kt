package net.drusantia.raidr.ui.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.databinding.FragmentCharacterBinding
import net.drusantia.raidr.utils.mythicPlusRuns
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by sharedViewModel<SearchViewModel>()
    private val characterObserver = Observer<PlayerCharacter?> { Timber.d("$it") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@CharacterFragment
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.character.observe(viewLifecycleOwner, characterObserver)
    }
}