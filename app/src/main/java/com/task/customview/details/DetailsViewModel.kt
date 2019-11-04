package com.task.customview.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.customview.entity.Element
import com.task.customview.model.interactors.IElementsInteractor
import com.task.customview.utils.BaseViewModel

class DetailsViewModel(private val interactor: IElementsInteractor) : BaseViewModel() {

    private val element = MutableLiveData<Element>()

    fun init(elementId: Int) = disposables.add(
        interactor.getElement(elementId)
            .doOnSubscribe { loadingData.value = true }
            .doAfterTerminate { loadingData.value = false }
            .subscribe(
                { element.value = it },
                { errorData.value = it })
    )

    fun getElement(): LiveData<Element> = element
}
