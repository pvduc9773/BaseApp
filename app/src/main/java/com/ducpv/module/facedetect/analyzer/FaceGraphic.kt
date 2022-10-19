package com.ducpv.module.facedetect.analyzer

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.google.mlkit.vision.face.Face

/**
 * Created by pvduc9773 on 28/10/20.
 */
class FaceGraphic(
    overlay: GraphicOverlay,
    private val face: Face
) : GraphicOverlay.Graphic(overlay) {
    private val paint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    override fun draw(canvas: Canvas) {
        val rect = face.boundingBox
        val top = translateY(rect.top.toFloat())
        val bottom = translateY(rect.bottom.toFloat())
        val left = translateX(rect.left.toFloat())
        val right = translateX(rect.right.toFloat())
        canvas.drawRect(left, top, right, bottom, paint)
    }
}
