package net.drusantia.raidr.ui.main

import android.annotation.SuppressLint
import android.os.*
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.LiveEvent
import net.drusantia.raidr.data.model.general.*
import net.drusantia.raidr.data.network.requestmodel.CharacterRequest
import net.drusantia.raidr.databinding.*
import net.drusantia.raidr.ui.base.GenericRecyclerAdapter
import net.drusantia.raidr.utils.extensions.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SearchFragment : Fragment() {
    companion object {
        private const val DEBOUNCE_TIME: Long = 500L
        private const val DEBOUNCE_PROGRESS_UPDATE_INTERVAL: Long = 10L
    }

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by sharedViewModel<SearchViewModel>()
    private val errorObserver = Observer<LiveEvent<Throwable?>> { event -> event.getAndClear()?.let { error -> showHttpErrorToast(error) } }
    private val searchObserver = Observer<SearchResultWrapper?> { updateSearchResults(it) }
    private val searchItemAdapter = object : GenericRecyclerAdapter<SearchResult, ItemSearchResultBinding>(mutableListOf()) {
        override val layoutResId = R.layout.item_search_result
        override fun onItemClick(model: SearchResult, position: Int) = onItemClicked(model)
        @SuppressLint("SetTextI18n")
        override fun onBindData(model: SearchResult, position: Int, binding: ItemSearchResultBinding) {
            binding.apply {
                name.text = model.name
                realm.text = "${model.data?.realm?.name}-${model.data?.region?.shortName}"
            }
        }
    }
    private val searchDebounceTimer = object : CountDownTimer(DEBOUNCE_TIME, DEBOUNCE_PROGRESS_UPDATE_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            isTyping = true
            binding.searchCharacterInclude.debounceTimer._visible()
            binding.searchCharacterInclude.debounceTimer.progress = ((millisUntilFinished * 100) / DEBOUNCE_TIME).toInt()
        }

        override fun onFinish() {
            isTyping = false
            binding.searchCharacterInclude.debounceTimer._gone()
            searchCharacterOrUser("${binding.searchCharacterInclude.search.text}")
        }
    }
    private var isTyping = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        init()
        return binding.root
    }

    private fun init() = binding.apply {
        lifecycleOwner = this@SearchFragment
        initSearchField()
        searchResults.adapter = searchItemAdapter
        viewModel.apply {
            searchResult.observe(viewLifecycleOwner, searchObserver)
            error.observe(viewLifecycleOwner, errorObserver)
        }
    }

    private fun FragmentSearchBinding.initSearchField() {
        searchCharacterInclude.search.apply {
            onEnterKey {
                searchCharacterOrUser("$text")
                setText(String.empty)
            }
            onTextChanged {
                if (it.isEmpty()) {
                    return@onTextChanged
                }

                if (isTyping) {
                    searchDebounceTimer.cancel()
                }
                searchDebounceTimer.start()
            }
        }
        searchCharacterInclude.clear.setOnClickListener {
            searchCharacterInclude.search.setText(String.empty)
        }
    }

    private fun searchCharacterOrUser(term: String) {
        searchDebounceTimer.cancel()
        isTyping = false
        if (!term.isBlank() && term.length >= 2) {
            viewModel.search(term)
        } else {
            searchItemAdapter.clearItems()
        }
    }

    private fun updateSearchResults(searchResultWrapper: SearchResultWrapper?) {
        Timber.d("$searchResultWrapper")
        if (searchResultWrapper?.matches != null) {
            searchItemAdapter.setItems(searchResultWrapper.matches)
        } else {
            searchItemAdapter.clearItems()
        }
    }

    private fun onItemClicked(model: SearchResult) {
        if (model.type == "guild") {
            Toast.makeText(activity, "Guilds are coming soon", Toast.LENGTH_LONG).show()
            return
        }

        val requestModel = CharacterRequest(
            region = model.data?.region?.shortName,
            realm = model.data?.realm?.name,
            name = model.name)
        viewModel.loadCharacter(requestModel)
        activity?.let {
            CharacterRioDetailsFragment().loadTo(R.id.container, it.supportFragmentManager, FragmentHelper.BackStackBehaviour.Add)
        }
    }
}