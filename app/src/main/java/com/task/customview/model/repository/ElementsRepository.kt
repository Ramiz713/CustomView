package com.task.customview.model.repository

import com.task.customview.R
import com.task.customview.entity.Element
import io.reactivex.Single

class ElementsRepository : IElementsRepository {

    override fun getElementsList(): Single<List<Element>> = Single.just(elementsList)

    override fun getElement(elementId: Int): Single<Element> =
        Single.just(elementsList.find { it.id == elementId })

    private val elementsList = listOf(
        Element(1, "Car", R.drawable.ic_local_car_wash_black_24dp),
        Element(2, "HTTPS", R.drawable.ic_https_black_24dp),
        Element(3, "Invert", R.drawable.ic_invert_colors_black_24dp),
        Element(4, "Shop", R.drawable.ic_shop_black_24dp),
        Element(5, "Open in new", R.drawable.ic_open_in_new_black_24dp),
        Element(6, "Delete sweep", R.drawable.ic_delete_sweep_black_24dp),
        Element(7, "Directions run", R.drawable.ic_directions_run_black_24dp),
        Element(8, "Domain", R.drawable.ic_domain_black_24dp),
        Element(9, "Fingerprint", R.drawable.ic_fingerprint_black_24dp),
        Element(10, "Loyalty", R.drawable.ic_loyalty_black_24dp),
        Element(11, "Content paste", R.drawable.ic_content_paste_black_24dp)
    )
}
