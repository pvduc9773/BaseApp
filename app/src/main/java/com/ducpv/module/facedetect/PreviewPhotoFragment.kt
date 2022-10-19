package com.ducpv.module.facedetect

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ducpv.base.BaseFragment
import com.ducpv.databinding.FragmentPreviewPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 20/10/20.
 */
@AndroidEntryPoint
class PreviewPhotoFragment : BaseFragment<PreviewPhotoViewModel, FragmentPreviewPhotoBinding>() {
    private val args by navArgs<PreviewPhotoFragmentArgs>()

    override val viewModel by viewModels<PreviewPhotoViewModel>()

    override fun getViewBinding() = FragmentPreviewPhotoBinding.inflate(layoutInflater)

    override fun initialize() {
        super.initialize()
        viewModel.setImageFile(args.image)
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.viewModel = viewModel
    }
}
