package com.ducpv.module.facedetect

import androidx.lifecycle.asLiveData
import com.ducpv.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject

/**
 * Created by pvduc9773 on 22/10/2022.
 */

data class UiState(
    val imageFile: File? = null
)

@HiltViewModel
class PreviewPhotoViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asLiveData()

    fun setImageFile(image: File) {
        _uiState.update { state ->
            state.copy(
                imageFile = image
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        _uiState.value.imageFile?.delete()
    }
}
