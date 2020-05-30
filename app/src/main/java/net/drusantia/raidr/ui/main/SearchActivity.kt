package net.drusantia.raidr.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.*
import net.drusantia.raidr.R
import net.drusantia.raidr.databinding.ActivitySingleContainerBinding
import net.drusantia.raidr.ui.base.BaseActivity
import net.drusantia.raidr.utils.extensions.*

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
        SearchFragment().loadTo(supportFragmentManager, backStackBehaviour = FragmentHelper.BackStackBehaviour.DoNotAdd)
    }
}