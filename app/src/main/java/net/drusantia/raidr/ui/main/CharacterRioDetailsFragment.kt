package net.drusantia.raidr.ui.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.*
import net.drusantia.raidr.databinding.*
import net.drusantia.raidr.ui.base.GenericRecyclerAdapter
import net.drusantia.raidr.utils.extensions.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class CharacterRioDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by sharedViewModel<SearchViewModel>()
    private val characterObserver = Observer<PlayerCharacter?> { character -> character?.let { setAdapter(it) } }
    private val openProfileInBrowser: View.OnClickListener = View.OnClickListener { viewModel.character.value?.profileUrl?.openUrlInBrowser(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false)
        initView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.character.observe(viewLifecycleOwner, characterObserver)
    }

    private fun initView() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@CharacterRioDetailsFragment
        }
    }

    private fun setAdapter(character: PlayerCharacter) {
        val items = mutableListOf<MythicPlusRun>().apply { addAll(character.mythicPlusBestRuns) }
        binding.apply {
            recycler.adapter = object : GenericRecyclerAdapter<MythicPlusRun, ItemCharacterMythicRunBinding>(items) {
                override val layoutResId: Int get() = R.layout.item_character_mythic_run
                override fun onBindData(model: MythicPlusRun, position: Int, binding: ItemCharacterMythicRunBinding) {
                    binding.model = model
                }

                override fun onItemClick(model: MythicPlusRun, position: Int) {
                    recycler.layoutManager?.findViewByPosition(position)?.let {
                        activity?.showTooltipAtTop("No details... yet. :)", it, false)
                    }
                }
            }
            characterDetails.apply {
                characterName.setOnClickListener(openProfileInBrowser)
                characterPortrait.setOnClickListener(openProfileInBrowser)
            }
        }
    }


}