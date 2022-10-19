package com.ducpv

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducpv.base.BaseFragment
import com.ducpv.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 26/10/2022.
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun viewBinding() {
        super.viewBinding()
        binding.buttonFaceDetect.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFaceDetectActivity()
            )
        }
    }
}
