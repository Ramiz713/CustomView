package com.task.customview.model.interactors

import com.task.customview.entity.Element
import com.task.customview.model.repository.IElementsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ElementsInteractor(private val repository: IElementsRepository) : IElementsInteractor {

    override fun getElementsList(): Single<List<Element>> =
        repository.getElementsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getElement(elementId: Int): Single<Element> =
        repository.getElement(elementId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
