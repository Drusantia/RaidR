@file:Suppress("unused")

package net.drusantia.raidr.utils.extensions

import android.content.*
import android.content.res.Resources
import android.net.Uri
import androidx.fragment.app.*
import net.drusantia.raidr.R
import retrofit2.HttpException
import kotlin.reflect.full.staticProperties

private val STRING_RESOURCE_ID_PREFIX by lazy { "@string/" }
private val STRING_RECURSIVE_IDS_REGEX by lazy { Regex("(($STRING_RESOURCE_ID_PREFIX)([a-z0-9_]+))") }

fun Context.getResolvedString(resId: Int) = resolveString(this.resources, resId)
fun Resources.getResolvedString(resId: Int) = resolveString(this, resId)
fun Fragment.getResolvedString(resId: Int) = resolveString(resources, resId)
fun String.resolveStringResourceReferences(resources: Resources) = resolveStringRecursively(resources, this)

fun Fragment.showHttpErrorToast(throwable: Throwable) = activity?.showHttpErrorToast(throwable)
fun Context.showHttpErrorToast(throwable: Throwable) {
    if (throwable is HttpException) {
        val message = throwable.message()
        if (!message.isNullOrBlank()) {
            coloredToast(message, android.R.color.holo_red_light, android.R.color.white, false)
        }
    }
}

fun String.openUrlInBrowser(fragment: Fragment) = fragment.activity?.let { openUrlInBrowser(it) }
fun String.openUrlInBrowser(activity: FragmentActivity) = activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this)))

private fun resolveString(resources: Resources, resId: Int): String {
    val originalString = resources.getString(resId)
    return if (!originalString.contains(STRING_RESOURCE_ID_PREFIX)) originalString
    else resolveStringRecursively(resources, originalString)
}

private fun resolveStringRecursively(resources: Resources, originalString: String): String {
    if (!originalString.contains(STRING_RESOURCE_ID_PREFIX))
        return originalString
    var resolvedString = originalString
    STRING_RECURSIVE_IDS_REGEX
        .findAll(originalString)
        .forEach { match ->
            val stringReferenceText = match.groups[0]?.value
            val stringReferenceName = match.groups[3]?.value
            stringReferenceName?.let {
                val referredResourceId = R.string::class
                    .staticProperties
                    .find { it.name == stringReferenceName }
                    ?.getter?.call() as Int?

                referredResourceId?.let {
                    resolvedString = resolvedString.replace(
                        stringReferenceText ?: String.empty,
                        resolveString(resources, it))
                }
            }
        }
    return resolvedString
}