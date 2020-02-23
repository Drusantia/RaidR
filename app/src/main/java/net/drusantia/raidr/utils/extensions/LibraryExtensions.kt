@file:Suppress("unused")

package net.drusantia.raidr.utils.extensions

import com.squareup.moshi.Moshi
import net.drusantia.raidr.data.network.adapter.EnumValueJsonAdapter
import net.drusantia.raidr.data.network.adapter.IValueEnum
import kotlin.reflect.KClass

fun <T : IValueEnum> Moshi.Builder.addValueEnum(kClass: KClass<T>): Moshi.Builder =
    add(kClass.java, EnumValueJsonAdapter.create(kClass.java).nullSafe())

fun <T : IValueEnum> Moshi.Builder.addValueEnums(vararg kClass: KClass<T>): Moshi.Builder {
    kClass.forEach { addValueEnum(it) }
    return this
}