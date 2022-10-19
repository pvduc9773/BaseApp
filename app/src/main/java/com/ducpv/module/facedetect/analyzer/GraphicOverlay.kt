package com.ducpv.module.facedetect.analyzer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View

/**
 * Created by pvduc9773 on 28/10/20.
 */
class GraphicOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mLock = Object()

    private val transformationMatrix = Matrix()
    private var imageWidth: Int = 0
    private var imageHeight: Int = 0
    private var scaleFactor = 1f

    private var postScaleWidthOffset = 0f
    private var postScaleHeightOffset = 0f

    private var needUpdateTransformation = false
    private var isImageFlipped = false
    private val graphics = mutableListOf<Graphic>()

    init {
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            needUpdateTransformation = true
        }
    }

    fun clear() {
        synchronized(mLock) {
            graphics.clear()
        }
        postInvalidate()
    }

    fun add(graphic: Graphic) {
        synchronized(mLock) {
            graphics.add(graphic)
        }
    }

    fun remove(graphic: Graphic) {
        synchronized(mLock) {
            graphics.remove(graphic)
        }
        postInvalidate()
    }

    fun setImageSourceInfo(imageWidth: Int, imageHeight: Int, isFlipped: Boolean) {
        assert(imageWidth > 0) { "image width must be positive" }
        assert(imageHeight > 0) { "image height must be positive" }
        synchronized(mLock) {
            this.imageWidth = imageWidth
            this.imageHeight = imageHeight
            this.isImageFlipped = isFlipped
            needUpdateTransformation = true
        }
        postInvalidate()
    }

    private fun updateTransformationIfNeeded() {
        if (!needUpdateTransformation || imageWidth <= 0 || imageHeight <= 0) {
            return
        }
        val viewAspectRatio = width.toFloat() / height
        val imageAspectRatio = imageWidth.toFloat() / imageHeight
        postScaleWidthOffset = 0f
        postScaleHeightOffset = 0f
        if (viewAspectRatio > imageAspectRatio) {
            scaleFactor = width.toFloat() / imageWidth
            postScaleHeightOffset = (width.toFloat() / imageAspectRatio - height) / 2
        } else {
            scaleFactor = height.toFloat() / imageHeight
            postScaleWidthOffset = (height.toFloat() * imageAspectRatio - width) / 2
        }

        transformationMatrix.reset()
        transformationMatrix.setScale(scaleFactor, scaleFactor)
        transformationMatrix.postTranslate(-postScaleWidthOffset, -postScaleHeightOffset)

        if (isImageFlipped) {
            transformationMatrix.postScale(-1f, 1f, width / 2f, height / 2f)
        }
        needUpdateTransformation = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(mLock) {
            updateTransformationIfNeeded()
            for (graphic in graphics) {
                graphic.draw(canvas)
            }
        }
    }

    abstract class Graphic(private val overlay: GraphicOverlay) {
        abstract fun draw(canvas: Canvas)

        private fun scale(imagePixel: Float): Float = imagePixel * overlay.scaleFactor

        fun translateX(x: Float): Float {
            return if (overlay.isImageFlipped) {
                overlay.width - (scale(x) - overlay.postScaleWidthOffset)
            } else {
                scale(x) - overlay.postScaleWidthOffset
            }
        }

        fun translateY(y: Float): Float = scale(y) - overlay.postScaleHeightOffset
    }
}
