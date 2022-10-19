package com.ducpv.module.facedetect.analyzer

import android.annotation.SuppressLint
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.odml.image.MediaMlImageBuilder
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions

/**
 * Created by pvduc9773 on 28/10/2022.
 */
class FaceDetectionAnalyzer(
    private val overlayView: GraphicOverlay? = null,
) : ImageAnalysis.Analyzer {
    private val faceDetector: FaceDetector

    init {
        val options = FaceDetectorOptions.Builder()
            .enableTracking()
            .build()
        faceDetector = FaceDetection.getClient(options)
    }

    fun stop() {
        faceDetector.close()
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val image = imageProxy.image ?: run {
            imageProxy.close()
            return
        }
        val sourceSize = imageProxy.size()
        setupOverlay(overlayView, sourceSize)
        val mlImage = MediaMlImageBuilder(image).setRotation(imageProxy.imageInfo.rotationDegrees).build()
        faceDetector.process(mlImage).addOnCompleteListener {
            imageProxy.close()
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            drawOverlay(overlayView, it.result)
        }
    }

    private fun setupOverlay(overlay: GraphicOverlay?, targetSize: Size) {
        overlay?.setImageSourceInfo(targetSize.width, targetSize.height, true)
    }

    private fun drawOverlay(overlay: GraphicOverlay?, faces: List<Face>) {
        if (overlay == null) return
        overlay.clear()
        for (face in faces) {
            overlay.add(FaceGraphic(overlay, face))
        }
        overlay.postInvalidate()
    }

    private fun ImageProxy.size(): Size {
        val rotationDegrees = imageInfo.rotationDegrees
        return if (rotationDegrees == 0 || rotationDegrees == 180) {
            Size(width, height)
        } else {
            Size(height, width)
        }
    }
}
