package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.accessor.RaiderIoCharacterAccessor
import org.koin.core.*

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
        val value = accessor.getCharacter()
        fenrohas.postValue(value)
    }
}