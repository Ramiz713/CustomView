package com.task.customview.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.task.customview.R
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    companion object {
        private const val ELEMENT_KEY = "element_key"

        fun create(imageTransitionName: String, textTransitionName: String, elementId: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("image", imageTransitionName)
                    putString("text", textTransitionName)
                    putInt(ELEMENT_KEY, elementId)
                }
            }
    }

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        ViewCompat.setTransitionName(
            view.findViewById<ImageView>(R.id.iv_icon),
            arguments?.getString("image")
        )
        ViewCompat.setTransitionName(
            view.findViewById<TextView>(R.id.tv_icon_name),
            arguments?.getString("text")
        )
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeError(view)
        observeLoading()
        observeGetElement()

        val elementId = arguments?.getInt(ELEMENT_KEY)
        detailsViewModel.init(elementId ?: 0)
    }

    private fun observeGetElement() =
        detailsViewModel.getElement().observe(viewLifecycleOwner, Observer {
            iv_icon.setImageResource(it.imageSrc)
            tv_icon_name.text = it.name
        })


    private fun observeLoading() =
        detailsViewModel.isLoading().observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

    private fun observeError(view: View) =
        detailsViewModel.error().observe(viewLifecycleOwner, Observer {
            Snackbar.make(view, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
        })
}
