package com.ducpv.module.facedetect

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Rational
import android.util.Size
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducpv.R
import com.ducpv.base.BaseFragment
import com.ducpv.databinding.FragmentTakeFacePhotoBinding
import com.ducpv.module.facedetect.analyzer.FaceDetectionAnalyzer
import com.ducpv.utils.defaultNavOptions
import com.ducpv.utils.extension.isPermissionGranted
import com.ducpv.utils.extension.showRationaleDialog
import com.ducpv.utils.settingsIntent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File

/**
 * Created by pvduc9773 on 20/10/2022.
 */
@AndroidEntryPoint
class TakeFacePhotoFragment : BaseFragment<TakeFacePhotoViewModel, FragmentTakeFacePhotoBinding>() {
    companion object {
        private val IMAGE_SIZE = Size(1080, 1080)
        private val CROP_RATIO = Rational(1, 1)
    }

    private val cameraPermission = Manifest.permission.CAMERA
    private val lensFacing = CameraSelector.LENS_FACING_FRONT

    private var previewView: PreviewView? = null
    private var captureUseCase: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

    private var faceDetectionAnalyzer: FaceDetectionAnalyzer? = null

    private val settingsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (requireContext().isPermissionGranted(cameraPermission)) {
            bindAllCameraUseCase()
        } else {
            requireActivity().finish()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            bindAllCameraUseCase()
        } else {
            if (!shouldShowRequestPermissionRationale(cameraPermission)) {
                requireContext().showRationaleDialog(
                    message = R.string.message_request_camera_permission_in_setting,
                    onPositiveListener = {
                        settingsLauncher.launch(settingsIntent)
                    },
                    onNegativeListener = {
                        requireActivity().finish()
                    }
                )
            } else {
                requireActivity().finish()
            }
        }
    }

    override val viewModel by viewModels<TakeFacePhotoViewModel>()

    override fun getViewBinding() = FragmentTakeFacePhotoBinding.inflate(layoutInflater)

    override fun initialize() {
        super.initialize()
        requestPermissionLauncher.launch(cameraPermission)
    }

    override fun viewBinding() {
        super.viewBinding()
        previewView = binding.preview
        viewModel.processCameraProvider.observe(viewLifecycleOwner) { processCameraProvider ->
            cameraProvider = processCameraProvider
            bindAllCameraUseCase()
        }
        binding.preview.setOnClickListener {
            captureImage()
        }
    }

    override fun onResume() {
        super.onResume()
        bindAllCameraUseCase()
    }

    override fun onPause() {
        super.onPause()
        faceDetectionAnalyzer?.stop()
        cameraProvider?.unbindAll()
    }

    private fun bindAllCameraUseCase() {
        if (cameraProvider == null) return
        cameraProvider?.unbindAll()
        bindPreviewUseCase()
        bindCaptureUseCase()
        bindFaceDetectionUseCase()
    }

    private fun bindFaceDetectionUseCase() {
        faceDetectionAnalyzer?.stop()
        faceDetectionAnalyzer = try {
            FaceDetectionAnalyzer(binding.overlayView)
        } catch (e: Exception) {
            Timber.e(e)
            return
        }
        val faceDetectionUseCase = ImageAnalysis.Builder().build()
        faceDetectionUseCase.setAnalyzer(
            ContextCompat.getMainExecutor(requireContext()),
            faceDetectionAnalyzer!!
        )
        cameraProvider?.bindToLifecycle(
            viewLifecycleOwner,
            cameraSelector,
            faceDetectionUseCase
        )
    }

    private fun bindPreviewUseCase() {
        val previewUseCase = Preview.Builder().build()
        previewUseCase.setSurfaceProvider(previewView?.surfaceProvider)
        cameraProvider?.bindToLifecycle(
            viewLifecycleOwner,
            cameraSelector,
            previewUseCase
        )
    }

    private fun bindCaptureUseCase() {
        captureUseCase = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .setTargetResolution(IMAGE_SIZE)
            .build()
        captureUseCase?.setCropAspectRatio(CROP_RATIO)
        cameraProvider?.bindToLifecycle(
            viewLifecycleOwner,
            cameraSelector,
            captureUseCase
        )
    }

    private fun captureImage() {
        val tmpFile = File.createTempFile("tmp", ".jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(tmpFile).build()
        captureUseCase?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val exif = ExifInterface(tmpFile)
                    val bitmap = BitmapFactory.decodeFile(tmpFile.path)
                    val clipped = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height)
                    clipped.compress(Bitmap.CompressFormat.JPEG, 100, tmpFile.outputStream())
                    exif.saveAttributes()
                    findNavController().navigate(
                        TakeFacePhotoFragmentDirections.actionTakeFacePhotoFragmentToPreviewPhotoFragment(tmpFile),
                        defaultNavOptions
                    )
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.e(exception)
                }
            }
        )
    }
}
