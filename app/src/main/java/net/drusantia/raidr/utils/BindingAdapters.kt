package net.drusantia.raidr.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.MythicPlusRun
import kotlin.math.roundToInt

@BindingAdapter("mythicPlusRuns")
fun TextView.mythicPlusRuns(mythicPlusRun: List<MythicPlusRun>?) {
    var sumScore = 0.0
    textFromHtml(mythicPlusRun
        ?.joinToString(separator = "\n") {
            sumScore += it.score
            "${it.dungeon} <b>${it.mythicLevel}</b>: <b>${it.score}</b><br/>"
        }
        ?.plus("<br/><br/><b>Current RIO: ${sumScore.roundToInt()}</b>")
        ?: context.resources.getString(R.string.loading))
}

@BindingAdapter("visibleIf")
fun View.visibleIf(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("textFromHtml")
fun TextView.textFromHtml(text: String?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT))
    } else {
        @Suppress("DEPRECATION")
        setText(Html.fromHtml(text))
    }
}