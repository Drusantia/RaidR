@file:Suppress("unused")

package net.drusantia.raidr.utils.extensions

import android.content.Context
import android.view.View
import com.squareup.moshi.Moshi
import it.sephiroth.android.library.xtooltip.*
import net.drusantia.raidr.R
import net.drusantia.raidr.data.network.adapter.*
import kotlin.reflect.KClass

fun <T : IValueEnum> Moshi.Builder.addValueEnum(kClass: KClass<T>): Moshi.Builder =
    add(kClass.java, EnumValueJsonAdapter.create(kClass.java).nullSafe())

fun <T : IValueEnum> Moshi.Builder.addValueEnums(vararg kClass: KClass<T>): Moshi.Builder {
    kClass.forEach { addValueEnum(it) }
    return this
}

fun Context.createTooltip(content: String, anchor: View) = Tooltip.Builder(this)
    .anchor(anchor)
    .text(content)
    .maxWidth(resources.displayMetrics.widthPixels / 2)
    .arrow(true)
    .closePolicy(ClosePolicy.Builder()
        .inside(true)
        .outside(true)
        .consume(true)
        .build())
    .styleId(R.style.RaidRToolTipStyle)
    .overlay(true)
    .create()

fun Context.showTooltipAtTop(content: String, anchor: View) =
    createTooltip(content, anchor)
        .show(anchor, Tooltip.Gravity.TOP, true)