package com.task.customview.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.customview.R
import com.task.customview.entity.Element
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_element.*

class ElementViewHolder(
    override val containerView: View,
    val clickListener: (ImageView, TextView, Int) -> Unit
) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun from(
            parent: ViewGroup,
            clickListener: (ImageView, TextView, Int) -> Unit
        ): ElementViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_element, parent, false)
            return ElementViewHolder(view, clickListener)
        }
    }

    fun bind(element: Element) = with(element) {
        iv_icon.setImageDrawable(containerView.context.getDrawable(imageSrc))
        tv_icon_name.text = name
        iv_icon.transitionName = "$id icon"
        tv_icon_name.transitionName = "$id text"
        itemView.setOnClickListener { clickListener(iv_icon, tv_icon_name, id) }
    }
}
