package net.drusantia.raidr.utils

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(
        private val beforeTextChanged: ((charSequence: CharSequence, start: Int, count: Int, after: Int) -> Unit)? = null,
        private val onTextChanged: ((charSequence: CharSequence, start: Int, before: Int, count: Int) -> Unit)? = null,
        private val afterChanged: ((editable: Editable) -> Unit)? = null
) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) =
            beforeTextChanged?.run { invoke(charSequence, start, count, after) } ?: Unit

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) =
            onTextChanged?.run { invoke(charSequence, start, before, count) } ?: Unit

    override fun afterTextChanged(editable: Editable) =
            afterChanged?.run { invoke(editable) } ?: Unit
}