package com.task.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.TintTypedArray
import androidx.core.content.ContextCompat

class HistogramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var dataSource: List<Float>

    private var rectangle = RectF()
    private var columnPaint: Paint
    private var textPaint: Paint

    private val defaultDp = 4f
    private var attrMarginColumns = 0f
    private var marginColumns = attrMarginColumns

    init {
        val a = TintTypedArray.obtainStyledAttributes(
            context, attrs, R.styleable.HistogramView, defStyleAttr, R.style.HistogramViewStyle
        )
        attrMarginColumns = a.getDimension(
            R.styleable.HistogramView_marginBetweenColumns, getDpInPixels(defaultDp, context)
        )

        columnPaint = Paint().apply {
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, R.color.colorAccent)
        }

        textPaint = Paint().apply {
            textSize = getSpInPixels(24f, context)
            style = Paint.Style.FILL
            color = Color.BLACK
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            if (!::dataSource.isInitialized)
                it.drawText(
                    context.resources.getString(R.string.empty_histogram),
                    width / 2f, height / 2f, textPaint
                )
            else drawColumns(it)
        }
    }

    fun setDataSource(data: List<Int>) {
        data.max()?.let {
            dataSource = data.map { element -> (1 - element.toFloat() / it) }
            marginColumns = attrMarginColumns
            invalidate()
        }
    }

    private fun drawColumns(canvas: Canvas) {
        val minX = paddingStart.toFloat()
        val minY = paddingTop.toFloat()
        val canvasWidth = width - paddingStart - paddingEnd
        val canvasHeight = height - paddingTop - paddingBottom

        val elementsCount = dataSource.count()
        val freeSpaceForColumns = findFreeSpaceForColumns(canvasWidth, elementsCount)
        val rectWidth = freeSpaceForColumns / elementsCount
        var rectLeftPosition = minX

        dataSource.forEach {
            rectangle.set(
                rectLeftPosition, canvasHeight * it + minY,
                rectLeftPosition + rectWidth, (height - paddingBottom).toFloat()
            )
            canvas.drawRoundRect(
                rectangle, getDpInPixels(8f, context),
                getDpInPixels(8f, context), columnPaint
            )
            rectLeftPosition += rectWidth + marginColumns
        }
    }

    private fun findFreeSpaceForColumns(canvasWidth: Int, elementsCount: Int): Float {
        var freeSpaceForColumns = canvasWidth - marginColumns * (elementsCount - 1)
        var suitableDp = defaultDp
        while (freeSpaceForColumns < 0) {
            marginColumns = getDpInPixels(suitableDp--, context)
            freeSpaceForColumns = canvasWidth - marginColumns * (elementsCount - 1)
        }
        return freeSpaceForColumns
    }
}
