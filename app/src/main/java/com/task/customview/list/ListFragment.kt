package com.task.customview.list

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.task.customview.R
import com.task.customview.details.DetailsFragment
import com.task.customview.list.adapter.ElementAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun create() = ListFragment()
    }

    private val listViewModel: ListViewModel by viewModel()
    private lateinit var clickedElementImageView: ImageView
    private lateinit var clickedElementTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeError(view)
        observeLoading()
        initRecycler()
        observeNavigationToDetailsScreen()
        observeGetElementsList()
    }

    private fun initRecycler() {
        val clickListener = { imageView: ImageView, textView: TextView, elementId: Int ->
            clickedElementImageView = imageView
            clickedElementTextView = textView
            listViewModel.elementItemClicked(elementId)
        }
        rv_elements.adapter = ElementAdapter(clickListener)

        rv_elements.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && tv_screen_title.visibility == View.GONE) {
                    tv_screen_title.visibility = View.VISIBLE
                    ValueAnimator.ofFloat(0f, 1f).apply {
                        addUpdateListener { tv_screen_title.alpha = it.animatedValue as Float }
                        duration = 300L
                        start()
                    }
                    return
                }
                val firstItem =
                    (rv_elements.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if (firstItem == 0) {
                    ValueAnimator.ofFloat(1f, 0f).apply {
                        addUpdateListener { tv_screen_title.alpha = it.animatedValue as Float }
                        duration = 300L
                        start()
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(p0: Animator?) {
                                tv_screen_title.visibility = View.GONE
                            }
                        })
                    }
                }
            }
        })
    }

    private fun observeGetElementsList() =
        listViewModel.getElementsList().observe(viewLifecycleOwner, Observer {
            (rv_elements.adapter as ElementAdapter).submitList(it)
        })

    private fun observeNavigationToDetailsScreen() =
        listViewModel.navigateToDetailsScreen.observe(viewLifecycleOwner, Observer {
            fragmentManager?.run {
                beginTransaction()
                    .addSharedElement(
                        clickedElementImageView,
                        clickedElementImageView.transitionName
                    )
                    .addSharedElement(clickedElementTextView, clickedElementTextView.transitionName)
                    .addToBackStack("detailsFragment")
                    .replace(
                        R.id.container,
                        DetailsFragment.create(
                            clickedElementImageView.transitionName,
                            clickedElementTextView.transitionName,
                            it ?: 0
                        )
                    )
                    .commit()
            }
        })

    private fun observeLoading() =
        listViewModel.isLoading().observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

    private fun observeError(view: View) =
        listViewModel.error().observe(viewLifecycleOwner, Observer {
            Snackbar.make(view, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
        })
}
