package net.drusantia.raidr.utils.extensions

import androidx.lifecycle.*

fun <T> MutableLiveData<T>.removeObserverAndValue(observer: Observer<T>) {
    this.removeObserver(observer)
    this.value = null
}

class NonNullValueObserver<T>(
        private val body: (T) -> Unit
) : Observer<T> {
    override fun onChanged(data: T?) {
        data?.let { body.invoke(it) }
    }
}