package com.task.customview.model.repository

import com.task.customview.entity.Element
import io.reactivex.Single

interface IElementsRepository {

    fun getElementsList(): Single<List<Element>>

    fun getElement(elementId: Int): Single<Element>
}
