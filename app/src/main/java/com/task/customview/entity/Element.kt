package com.task.customview.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Element(
    val id: Int,
    val name: String,
    val imageSrc: Int
) : Parcelable
