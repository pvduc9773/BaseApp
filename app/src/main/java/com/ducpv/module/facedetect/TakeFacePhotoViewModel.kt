package com.ducpv.module.facedetect

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducpv.base.BaseViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by pvduc9773 on 22/10/2022.
 */
@HiltViewModel
class TakeFacePhotoViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel() {
    private val _processCameraProvider = MutableLiveData<ProcessCameraProvider>()
    val processCameraProvider: LiveData<ProcessCameraProvider>
        get() = _processCameraProvider

    init {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(getApplication(context))
        cameraProviderFuture.addListener({
            try {
                _processCameraProvider.value = cameraProviderFuture.get()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }, ContextCompat.getMainExecutor(getApplication(context)))
    }
}
