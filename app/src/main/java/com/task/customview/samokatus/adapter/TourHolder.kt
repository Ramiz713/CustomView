package com.task.customview.samokatus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.customview.R
import com.task.customview.samokatus.entity.Tour
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tour.*

class TourHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun from(parent: ViewGroup): TourHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_tour, parent, false)
            return TourHolder(view)
        }
    }

    fun bind(tour: Tour) = with(containerView.context) {
        iv_tour_icon.setImageDrawable(getDrawable(tour.iconSrc))
        tv_tour_title.text = tour.name
        tv_tour_descr.text = tour.description
    }

}
