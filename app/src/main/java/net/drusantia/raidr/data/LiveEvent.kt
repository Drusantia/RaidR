@file:Suppress("unused")

package net.drusantia.raidr.data

open class LiveEvent<out T>(private val content: T) {
    private var isConsumed = false

    /** Returns the content and prevents its use again. */
    fun getAndClear(): T? {
        return if (isConsumed) null
        else {
            isConsumed = true
            content
        }
    }

    /** Returns the content, even if it's already been handled. Will not set it to be handled. */
    fun peek(): T = content

    /** Reactivates the handled content, so it acts as unhandled */
    fun reactivate() {
        isConsumed = false
    }

    /** Returns if the content was already consumed by [getAndClear]. */
    fun isConsumed() = isConsumed

    override fun toString(): String {
        return when {
            content == null -> "null"
            isConsumed -> "Consumed!: $content"
            !isConsumed -> "$content"
            else -> "$content"
        }
    }
}

fun <T> LiveEvent<T>.ifTrue(block: (LiveEvent<T>) -> Unit): LiveEvent<T> {
    this.getAndClear()
        ?.let {
            if (it is Boolean && it == true)
                block(this)
        }
    return this
}

fun <T> LiveEvent<T>.ifFalse(block: (LiveEvent<T>) -> Unit): LiveEvent<T> {
    this.getAndClear()
        ?.let {
            if (it is Boolean && it == false)
                block(this)
        }
    return this
}

fun <T> LiveEvent<T>.ifElse(
    ifBlock: (LiveEvent<T>) -> Unit,
    elseBlock: (LiveEvent<T>) -> Unit
): LiveEvent<T> {
    this.getAndClear()
        ?.let {
            if (it is Boolean) {
                if (it == true) ifBlock(this)
                else elseBlock(this)
            }
        }
    return this
}