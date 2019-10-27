package com.task.customview.samokatus.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.task.customview.samokatus.entity.Filter

class FilterAdapter : ListAdapter<Filter, FilterHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder =
        FilterHolder.from(parent)

    override fun onBindViewHolder(holder: FilterHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Filter>() {
            override fun areItemsTheSame(
                oldItem: Filter,
                newItem: Filter
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Filter,
                newItem: Filter
            ): Boolean = oldItem == newItem
        }
    }
}
