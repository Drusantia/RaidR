package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.LiveEvent
import net.drusantia.raidr.data.model.PlayerCharacter
import net.drusantia.raidr.data.repository.RaiderIoCharacterRepository
import net.drusantia.raidr.data.repository.RaiderIoCharacterRepository.RequestKeys.FENROHAS
import net.drusantia.raidr.utils.StoreResult
import org.koin.core.*

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModel : ViewModel(), KoinComponent {
    private val repository: RaiderIoCharacterRepository by inject()

    val character = MutableLiveData<PlayerCharacter?>()
    val error = MutableLiveData<LiveEvent<Throwable?>>(LiveEvent(null))

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    @ExperimentalCoroutinesApi
    fun load() = viewModelScope.launch {
        repository.getCharacterStream(FENROHAS, StoreResult(
            onError = { error.postValue(LiveEvent(it)) },
            onFinished = { character.postValue(it) }))
    }
}