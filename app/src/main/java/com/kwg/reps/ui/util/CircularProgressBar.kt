package com.kwg.reps.ui.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircularProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val progressPaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 20f
        isAntiAlias = true
    }

    private val backgroundPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = 20f
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    private var progress: Int = 0 // Progress in percentage (0-100)
    private val maxProgress: Int = 100
    private val strokeWidth: Float = 20f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY) - (strokeWidth / 2)

        // Draw the background circle
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint)

        // Draw the progress arc
        val sweepAngle = (360f * progress) / maxProgress
        canvas.drawArc(
            centerX - radius, centerY - radius,
            centerX + radius, centerY + radius,
            -90f, sweepAngle, false, progressPaint
        )

        // Draw the percentage text
        val percentageText = "$progress%"
        canvas.drawText(percentageText, centerX, centerY + (textPaint.textSize / 3), textPaint)
    }

    // Method to update the progress
    fun setProgress(progress: Int) {
        this.progress = progress.coerceIn(0, maxProgress) // Clamp between 0 and max
        invalidate() // Redraw the view
    }
}