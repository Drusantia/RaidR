@file:Suppress("unused")

package net.drusantia.raidr.utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.*
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.*
import net.drusantia.raidr.utils.extensions.px2dp
import java.text.ParseException
import java.util.concurrent.TimeUnit


@BindingAdapter("mythicPlusRuns")
fun TextView.mythicPlusRuns(mythicPlusRun: List<MythicPlusRun>?) {
    var sumScore = 0.0
    textFromHtml(mythicPlusRun
        ?.joinToString(separator = "\n") {
            sumScore += it.score
            "${it.dungeon} <b>${it.mythicLevel}</b>: <b>${it.score}</b><br/>"
        }
        ?.plus("<br/><b>Current RIO: ${String.format("%.2f", sumScore)}</b>")
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

@BindingAdapter("dateTime")
fun TextView.dateTime(isoDateTime: String) {
    text = try {
        isoDateFormat.parse(isoDateTime).toReadableDateTime()
    } catch (e: ParseException) {
        "n/a"
    }
}

@BindingAdapter("affixImages")
fun LinearLayout.addAffixImages(affixes: List<Affix>) {
    affixes.forEachIndexed { index, affix ->
        val iv = ImageView(context).apply {
            val drawable = ContextCompat.getDrawable(context, affix.getDrawableId(context))
            setImageDrawable(drawable)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(left, top, if (index == affixes.size - 1) right else 4.px2dp(context), bottom)
            layoutParams = lp
            setOnClickListener { Toast.makeText(context, "${affix.name}: ${affix.description}", Toast.LENGTH_LONG).show() }
        }
        addView(iv)
    }
}

@BindingAdapter("keystoneLevelUpgrade")
fun TextView.keystoneLevelUpgrade(level: Int) {
    text = if (level > 0) "+$level" else "BROKEN"
}

@BindingAdapter("msToReadableTime")
fun TextView.msToReadableTime(ms: Long) {
    val hours: Long = TimeUnit.MILLISECONDS.toHours(ms)
    val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(ms) % 60
    val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(ms) % 60
    text = if (hours > 0) {
        String.format("%d:%d:%d", hours, minutes, seconds)
    } else {
        String.format("%d:%d", minutes, seconds)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("characterDescription")
fun TextView.characterDescription(character: PlayerCharacter?) {
    character?.run {
        text = "$activeSpecName $race $className of the $factionName"
    }
}