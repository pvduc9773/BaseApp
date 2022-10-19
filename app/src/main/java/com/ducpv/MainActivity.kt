package com.ducpv

import androidx.activity.viewModels
import com.ducpv.base.BaseActivity
import com.ducpv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}
