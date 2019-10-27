package com.task.customview.home.entity

data class Payment(
    val id: Int,
    val description: String,
    val ownerId: Int,
    val operation: PaymentOperation,
    val sum: Double,
    val date: String
)
