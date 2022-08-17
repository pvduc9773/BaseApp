package com.stdio

import androidx.activity.viewModels
import com.stdio.base.BaseActivity
import com.stdio.databinding.ActivityMainBinding
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