package com.task.customview.samokatus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.customview.R
import com.task.customview.samokatus.entity.Filter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_filter.*

class FilterHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun from(parent: ViewGroup): FilterHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_filter, parent, false)
            return FilterHolder(view)
        }
    }

    fun bind(filter: Filter) = with(containerView.context) {
        iv_filter_icon.setImageDrawable(getDrawable(filter.iconSrc))
        card_container.background = getDrawable(filter.backgroundSrc)
        tv_filter_name.text = filter.name
    }
}
