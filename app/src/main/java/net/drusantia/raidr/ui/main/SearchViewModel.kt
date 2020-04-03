package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.LiveEvent
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.model.general.*
import net.drusantia.raidr.data.network.requestmodel.CharacterRequest
import net.drusantia.raidr.data.repository.*
import net.drusantia.raidr.utils.StoreResult
import org.koin.core.*
import timber.log.Timber

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SearchViewModel : ViewModel(), KoinComponent {
    private val characterRepository: RaiderIoCharacterRepository by inject()
    private val generalRepository: RaiderIoGeneralRepository by inject()

    val character = MutableLiveData<PlayerCharacter?>()
    val searchResult = MutableLiveData<SearchResultWrapper?>()
    val error = MutableLiveData<LiveEvent<Throwable?>>(LiveEvent(null))

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    @ExperimentalCoroutinesApi
    fun search(term: String) = viewModelScope.launch {
        generalRepository.searchStream(term, StoreResult(
            onLoading = { searchResult.postValue(null) },
            onError = { error.postValue(LiveEvent(it)) },
            onFinished = { searchResult.postValue(it) }))
    }

    @ExperimentalCoroutinesApi
    fun loadCharacter(requestModel: CharacterRequest) = viewModelScope.launch {
        characterRepository.getCharacterStream(requestModel, StoreResult(
            onLoading = { Timber.i("Loading"); character.postValue(null) },
            onError = { Timber.e("Error, $it"); error.postValue(LiveEvent(it)) },
            onFinished = { Timber.i("Loaded: $it"); character.postValue(it) }))
    }
}