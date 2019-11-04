package com.task.customview.list.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.task.customview.entity.Element

class ElementAdapter(val clickListener: (ImageView, TextView, Int) -> Unit) :
    ListAdapter<Element, ElementViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder =
        ElementViewHolder.from(parent, clickListener)

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Element>() {

            override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean =
                oldItem == newItem
        }
    }
}
