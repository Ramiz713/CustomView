package com.task.customview

import android.content.Context
import android.util.TypedValue

fun getDpInPixels(dp: Float, context: Context) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp,
        context.resources.displayMetrics
    )

fun getSpInPixels(sp: Float, context: Context) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, sp,
        context.resources.displayMetrics
    )
