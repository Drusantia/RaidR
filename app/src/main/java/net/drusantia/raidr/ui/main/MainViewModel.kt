package net.drusantia.raidr.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val hello = MutableLiveData<String>("Data binding works!")
}
