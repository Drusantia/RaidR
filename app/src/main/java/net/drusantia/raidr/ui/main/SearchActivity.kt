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
        init()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_container)
        SearchFragment().loadTo(
            R.id.container,
            supportFragmentManager,
            FragmentHelper.BackStackBehaviour.DoNotAdd,
            SearchFragment::class.java.simpleName)
    }
}