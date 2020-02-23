package net.drusantia.raidr.data.network.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

class EnumValueJsonAdapter<T : IValueEnum>
private constructor(
    private val enumType: Class<T>
) : JsonAdapter<T>() {
    companion object {
        fun <T : IValueEnum> create(enumType: Class<T>): EnumValueJsonAdapter<T> = EnumValueJsonAdapter(enumType)
    }

    private val constants: Array<T> = enumType.enumConstants
    private val enumValues: HashMap<Int, T> = constants.associateByTo(hashMapOf()) { it.value }

    override fun fromJson(reader: JsonReader): T? {
        val value = reader.nextInt()
        return if (enumValues.containsKey(value)) {
            enumValues[value]
        } else null
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        value?.value?.let {
            writer.value(it)
        }
    }

    override fun toString() = "EnumJsonAdapter(" + enumType.name + ")"
}