package com.task.customview.samokatus.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.task.customview.samokatus.entity.Tour

class TourAdapter : ListAdapter<Tour, TourHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourHolder =
        TourHolder.from(parent)

    override fun onBindViewHolder(holder: TourHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tour>() {
            override fun areItemsTheSame(
                oldItem: Tour,
                newItem: Tour
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Tour,
                newItem: Tour
            ): Boolean = oldItem == newItem
        }
    }
}
