package net.drusantia.raidr.utils.extensions

import android.graphics.Typeface
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

val String.Companion.empty get() = ""
val String.Companion.space get() = " "
val String.Companion.tab get() = "\t"
val String.Companion.newLine get() = "\n"

fun String.containsAny(vararg stringsToSearch: String, ignoreCase: Boolean = false) =
        stringsToSearch.any { contains(it, ignoreCase) }

fun String.startsWithAnyOf(vararg stringsToSearch: String, ignoreCase: Boolean = false) =
        stringsToSearch.any { startsWith(it, ignoreCase) }

fun String.endsWithAnyOf(vararg stringsToSearch: String, ignoreCase: Boolean = false) =
        stringsToSearch.any { endsWith(it, ignoreCase) }

@Suppress("DEPRECATION")
fun String.fromHTML(): Spanned {
    return if (SDK_INT < N) {
        Html.fromHtml(this) // This one is deprecated but needs to be used for pre-24 (N) versions
    } else {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    }
}

/** Colors text that are between [[, and ]].
 * eg. in "Hello [[world]], what's [[up]]?", the words "world" and "up" are being colorized.
 * Use it as: textView.setText("Hello [[world]]!! This [[is a]] long string to [[colorize]]!!".colorizeBracketed(resolvedColor), TextView.BufferType.Spannable)
 * @param [color]    the color to set for the matching parts.
 * 					Use ContextCompat.getColor(context, R.color.color_id) value
 * @param [isHighlightColor]    indicates if the color should use as a highlight color (background).
 * 								If not used as highlight color (default) it will apply as text color.
 * @param [makeBold]    indicates if the matching texts should be made bold. Default is false */
fun String.colorizeBracketed(color: Int, isHighlightColor: Boolean = false, makeBold: Boolean = false): Spannable {
    var inputString = this
    val pattern: Regex = "\\[(.*?)\\]".toRegex()
    val spannable = SpannableStringBuilder(inputString)
    pattern.findAll(inputString)
            .forEach {
                val match = it.groupValues[0] // 0: [text], 1: text
                val startIndex = inputString.indexOf(match)
                val endIndex = startIndex + match.length

                spannable.setSpan(
                        if (isHighlightColor) BackgroundColorSpan(color) else ForegroundColorSpan(color),
                        startIndex + 1,
                        endIndex - 1,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE)

                if (makeBold)
                    spannable.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

                inputString = inputString.removeRange(endIndex - 1, endIndex) // remove "]"
                inputString = inputString.removeRange(startIndex, startIndex + 1) // remove "["
                spannable.delete(endIndex - 1, endIndex) // remove "]"
                spannable.delete(startIndex, startIndex + 1) // remove "["
            }
    return spannable
}

/** Validates String with the given validator */
fun String.isValid(
        validator: (String) -> Boolean,
        errorMessage: String = "Invalid input"
): Pair<Boolean, String?> =
        if (!validator(this)) false to errorMessage
        else true to null


/** Validates String with the given validators. Stops validation at first error, if any.*/
fun String.isValid(validators: Array<InputValidator>): Pair<Boolean, String?> {
    validators.forEach {
        val validation = this.isValid(it.validator, it.errorMessage)
        if (!validation.first)
            return validation
    }
    return true to null
}

fun String.addHyphenBetweenEveryXChars(groupSize: Int = 2): String {
    val sb = StringBuilder()
    chunked(groupSize).forEachIndexed { index, content ->
        sb.append(content)
        if (index <= content.lastIndex)
            sb.append("-")
    }
    return sb.toString()
}

fun String.toCamelCase() = toPascalOrCamelCase(false)
fun String.toPascalCase() = toPascalOrCamelCase(true)
private fun String.toPascalOrCamelCase(toPascalCase: Boolean = true): String {
    val delimiters = " '-/"
    val sb = StringBuilder()
    var capNext = toPascalCase

    forEach {
        sb.append(if (capNext) Character.toUpperCase(it) else Character.toLowerCase(it))
        capNext = delimiters.contains(it, true)
    }

    return sb.toString()
}