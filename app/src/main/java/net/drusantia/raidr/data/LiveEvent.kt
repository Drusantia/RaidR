package net.drusantia.raidr.data

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 *
 * Practical extra use cases for [Boolean] values:
 * val demo = LiveEvent(true)
 * // These consumes the data and returns null on the next read (or in case of ifTrue, ifFalse, ifElse, no code block is being executed)
 * demo.getAndClear()?.let { /* use [it], it is true of false */ }
 * demo.ifTrue { /* execute some code. the value is true here */ }
 * demo.ifFalse { /* execute some code. the value is false here */ }
 * demo.ifElse({ /* execute some code. the value is true here */ }, { /* execute some code. the value is false here */ })
 * Note for ifElse that it needs to be called as a function, because it has 2 code blocks.
 *
 * // These uses [peek], so the data isn't being consumed.
 * demo.peek().letIfFalse { /* execute some code. the value is false here */ }
 * demo.peek().letIfTrue { /* execute some code. the value is true here */ }
 * demo.peek().letIfElse({ /* execute some code. the value is true here */ }, { /* execute some code. the value is false here */ })
 * Note that these are different extension functions (ifTrue vs letIfTrue, etc)
 * */
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

/** This is a special type of [let].
 * You can call it on anything (that's the point), but the code block only executes, if
 * the object is a [Boolean] or [Boolean?], and the value is [true].
 * Note: weather the block runs or not, the value is being consumed.
 * @param block the code block to execute. Can be used as a simple lambda.
 * @return the object that it's been called on. It has been consumed, but can be [reactivate]ed or [peek]ed. */
fun <T> LiveEvent<T>.ifTrue(block: (LiveEvent<T>) -> Unit): LiveEvent<T> {
    this.getAndClear()
        ?.let {
            if (it is Boolean && it == true)
                block(this)
        }
    return this
}

/** This is a special type of [let].
 * You can call it on anything (that's the point), but the code block only executes, if
 * the object is a [Boolean] or [Boolean?], and the value is [false].
 * Note: weather the block runs or not, the value is being consumed.
 * @param block the code block to execute. Can be used as a simple lambda.
 * @return the object that it's been called on. It has been consumed, but can be [reactivate]ed or [peek]ed. */
fun <T> LiveEvent<T>.ifFalse(block: (LiveEvent<T>) -> Unit): LiveEvent<T> {
    this.getAndClear()
        ?.let {
            if (it is Boolean && it == false)
                block(this)
        }
    return this
}

/** This is a special type of [let].
 * You can call it on anything (that's the point), but the code block only executes, if
 * the object is a [Boolean] or [Boolean?].
 * Note: weather any of the blocks run or not, the value is being consumed.
 * Note: this can't be called as a simple lambda, you need to provide 2 lambdas as function arguments.
 * @param ifBlock the code block to execute if the value is [true]
 * @param elseBlock the code block to execute if the value is [false]
 * @return the object that it's been called on. It has been consumed, but can be [reactivate]ed or [peek]ed. */
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