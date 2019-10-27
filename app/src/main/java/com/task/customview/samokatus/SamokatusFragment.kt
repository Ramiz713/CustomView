package com.task.customview.samokatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.task.customview.MockedRepository
import com.task.customview.R
import com.task.customview.samokatus.adapter.FilterAdapter
import com.task.customview.samokatus.adapter.TourAdapter
import kotlinx.android.synthetic.main.fragment_samokatus.*

class SamokatusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_samokatus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(tb_samokatus as Toolbar)
        initRecycler()
    }

    private fun initRecycler() {
        rv_filters.adapter = FilterAdapter()
        (rv_filters.adapter as FilterAdapter).submitList(MockedRepository().getFilterItems())


        rv_tours.adapter = TourAdapter()
        (rv_tours.adapter as TourAdapter).submitList(MockedRepository().getTourItems())
    }
}
