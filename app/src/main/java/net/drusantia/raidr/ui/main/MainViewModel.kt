package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.network.accessor.RaiderIoCharacterAccessor
import net.drusantia.raidr.data.model.character.PlayerCharacter
import org.koin.core.*

class MainViewModel : ViewModel(), KoinComponent {
    private val accessor: RaiderIoCharacterAccessor by inject()

    val fenrohas = MutableLiveData<PlayerCharacter>()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun load() = viewModelScope.launch {
        val x = accessor.getFenrohas()
        fenrohas.postValue(x)
    }
}
