package net.drusantia.raidr.ui.main

import androidx.lifecycle.*
import com.dropbox.android.external.store4.get
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.accessor.RaiderIoCharacterAccessor
import org.koin.core.*

class MainViewModel : ViewModel(), KoinComponent {
    private val accessor: RaiderIoCharacterAccessor by inject()

    val fenrohas = MutableLiveData<PlayerCharacter>()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    @ExperimentalCoroutinesApi
    fun load() = viewModelScope.launch {
        val value = accessor.fenrohas().get(Unit)
        fenrohas.postValue(value)
//            .stream(StoreRequest.cached(refresh = false, key = Unit))
//            .collect { response ->
//                when (response) {
//                    is StoreResponse.Loading -> fenrohas.postValue(null)
//                    is StoreResponse.Error -> fenrohas.postValue(null)
//                    is StoreResponse.Data -> {
//                        Timber.d(
//                            "Value from ${when (response.origin) {
//                                ResponseOrigin.Fetcher -> "Fetcher (API)"
//                                ResponseOrigin.Cache -> "Cache (memory)"
//                                ResponseOrigin.Persister -> "Persister (disc)"
//                            }}")
//                        fenrohas.postValue(response.value)
//                    }
//                }
//            }

//        val x = accessor.getFenrohas()
//        fenrohas.postValue(x)
    }
}