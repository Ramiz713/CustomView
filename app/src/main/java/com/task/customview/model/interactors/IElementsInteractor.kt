package com.task.customview.model.interactors

import com.task.customview.entity.Element
import io.reactivex.Single

interface IElementsInteractor {

    fun getElementsList(): Single<List<Element>>

    fun getElement(elementId: Int): Single<Element>
}
