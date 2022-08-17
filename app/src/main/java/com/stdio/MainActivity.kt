package com.stdio

import androidx.activity.viewModels
import com.stdio.base.BaseActivity
import com.stdio.base.BaseViewModel
import com.stdio.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.uiState.observe(this) {
            when (it) {
                is BaseViewModel.BaseState.Loading -> {
                    binding.textView.text = "Loading..."
                }
                is BaseViewModel.BaseState.Success -> {
                    binding.textView.text = it.value
                }
                is BaseViewModel.BaseState.Error -> {
                    binding.textView.text = it.message
                }
            }
        }
    }
}