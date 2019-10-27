package com.task.customview

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun formatSum(num: Double): String {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH) as DecimalFormat
    val symbols = formatter.decimalFormatSymbols
    symbols.groupingSeparator = ' '
    formatter.decimalFormatSymbols = symbols
    formatter.applyPattern("###,##0.00")
    return formatter.format(num)
}
