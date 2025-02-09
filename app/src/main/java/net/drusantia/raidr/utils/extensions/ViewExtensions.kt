@file:Suppress("FunctionName", "unused")

package net.drusantia.raidr.utils.extensions

import android.view.View
import android.widget.EditText
import net.drusantia.raidr.utils.SimpleTextWatcher

fun View._isVisible() = visibility == View.VISIBLE
fun View._isInvisible() = visibility == View.INVISIBLE
fun View._isGone() = visibility == View.GONE

fun View._gone() {
    visibility = View.GONE
}

fun View._invisible() {
    visibility = View.INVISIBLE
}

fun View._visible() {
    visibility = View.VISIBLE
}

fun EditText.onTextChanged(action: (text: String) -> Unit) = addTextChangedListener(SimpleTextWatcher(afterChanged = { action("$it") }))