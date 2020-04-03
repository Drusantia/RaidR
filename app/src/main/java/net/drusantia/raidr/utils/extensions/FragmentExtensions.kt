@file:Suppress("unused")

package net.drusantia.raidr.utils.extensions

import androidx.fragment.app.*

object FragmentHelper {
    enum class BackStackBehaviour { Add, DoNotAdd }

    val DEFAULT_ADD_TO_BACK_STACK = BackStackBehaviour.Add
}

fun <T : Fragment> T.loadTo(
    containerId: Int,
    fragmentManager: FragmentManager,
    backStackBehaviour: FragmentHelper.BackStackBehaviour = FragmentHelper.DEFAULT_ADD_TO_BACK_STACK,
    tagName: String? = this::class.java.simpleName
) = fragmentManager
    .beginTransaction()
    .run {
        replace(containerId, this@loadTo, tagName)
        if (backStackBehaviour == FragmentHelper.BackStackBehaviour.Add)
            addToBackStack(null)
        commit()
    }

fun <T : Fragment> T.addTo(
    containerId: Int,
    fragmentManager: FragmentManager,
    backStackBehaviour: FragmentHelper.BackStackBehaviour = FragmentHelper.DEFAULT_ADD_TO_BACK_STACK,
    tagName: String? = this::class.java.simpleName
) = fragmentManager
    .beginTransaction()
    .run {
        add(containerId, this@addTo, tagName)
        if (backStackBehaviour == FragmentHelper.BackStackBehaviour.Add)
            addToBackStack(null)
        commit()
    }