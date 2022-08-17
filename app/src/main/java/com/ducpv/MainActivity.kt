package com.ducpv

import androidx.activity.viewModels
import com.ducpv.base.BaseActivity
import com.ducpv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun viewBinding() {
        super.viewBinding()
        binding.viewModel = viewModel
    }
}