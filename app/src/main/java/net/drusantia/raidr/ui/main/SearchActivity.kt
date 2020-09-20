package net.drusantia.raidr.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.databinding.ActivitySingleContainerBinding
import net.drusantia.raidr.ui.base.BaseActivity
import net.drusantia.raidr.utils.extensions.*
import net.drusantia.raidr.utils.extensions.FragmentHelper.BackStackBehaviour

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SearchActivity : BaseActivity() {
    private lateinit var binding: ActivitySingleContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_container)
        if (savedInstanceState == null) {
            init()
        }
    }

    private fun init() {
//        onPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, onDeniedAction = {
//            coloredToast("Permission denied", android.R.color.holo_red_dark, android.R.color.white, false)
//        }) {
//            coloredToast("Permission granted.", android.R.color.holo_green_dark, android.R.color.white, false)
//        }
//
//        onWriteStoragePermission {
//            coloredToast("Permission granted.", android.R.color.holo_green_dark, android.R.color.white, false)
//        }

        SearchFragment().loadTo(supportFragmentManager, backStackBehaviour = BackStackBehaviour.DoNotAdd)
    }
}