package net.drusantia.raidr.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.drusantia.raidr.R
import net.drusantia.raidr.data.model.character.MythicPlusRun
import kotlin.math.roundToInt

@BindingAdapter("mythicPlusRuns")
fun TextView.mythicPlusRuns(mythicPlusRun: List<MythicPlusRun>?) {
    var sumScore = 0.0
    text = mythicPlusRun
        ?.joinToString(separator = "\n") {
            sumScore += it.score
            "${it.dungeon} ${it.mythicLevel}: ${it.score}"
        }
        ?.plus("\n\nCurrent RIO: ${sumScore.roundToInt()}")
        ?: context.resources.getString(R.string.loading)
}