package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.accessor.RaiderIoCharacterAccessor
import org.koin.core.*
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel : ViewModel(), KoinComponent {
    private val accessor: RaiderIoCharacterAccessor by inject()

    val fenrohas = MutableLiveData<PlayerCharacter?>()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    @ExperimentalCoroutinesApi
    fun load() = viewModelScope.launch {
        //        val value = accessor.getCharacter(RaiderIoCharacterAccessor.RequestKeys.FENRO)
        try {
            val value = accessor.getCharacter()
            fenrohas.postValue(value)
        } catch (e: Exception) {
            Timber.e(e)
            fenrohas.postValue(null)
        }
    }
}