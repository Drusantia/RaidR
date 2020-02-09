package net.drusantia.raidr.application

import android.app.*
import android.os.Bundle
import net.drusantia.raidr.utils.extensions.empty
import timber.log.Timber

class ActivityLifecycleLogCallback : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) = activity?.run {
        Timber.i("${this::class.java.simpleName} paused")
    } ?: Unit

    override fun onActivityResumed(activity: Activity?) = activity?.run {
        Timber.i("${this::class.java.simpleName} resumed")
    } ?: Unit

    override fun onActivityStarted(activity: Activity?) = activity?.run {
        Timber.i("${this::class.java.simpleName} started")
    } ?: Unit

    override fun onActivityDestroyed(activity: Activity?) = activity?.run {
        Timber.i("${this::class.java.simpleName} destroyed (${if (isFinishing) String.empty else "not "}finished)")
    } ?: Unit

    override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) = activity?.run {
        Timber.i("${this::class.java.simpleName} saveInstanceState |> ${bundle ?: "bundle: null"}")
    } ?: Unit

    override fun onActivityStopped(activity: Activity?) = activity?.run {
        Timber.i("${this::class.java.simpleName} stopped")
    } ?: Unit

    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) = activity?.run {
        Timber.i("${this::class.java.simpleName} created  |> ${bundle ?: "bundle: null"}")
    } ?: Unit
}