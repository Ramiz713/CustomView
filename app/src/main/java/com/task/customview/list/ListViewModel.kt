package com.task.customview.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.customview.entity.Element
import com.task.customview.model.interactors.IElementsInteractor
import com.task.customview.utils.BaseViewModel
import com.task.customview.utils.SingleLiveEvent

class ListViewModel(private val interactor: IElementsInteractor) : BaseViewModel() {

    init {
        disposables.add(
            interactor.getElementsList()
                .doOnSubscribe { loadingData.value = true }
                .doAfterTerminate { loadingData.value = false }
                .subscribe(
                    { elementsList.value = it },
                    { errorData.value = it })
        )
    }

    private val elementsList = MutableLiveData<List<Element>>()
    private val elementItemClickedData = SingleLiveEvent<Int>()

    val navigateToDetailsScreen: LiveData<Int?> = elementItemClickedData

    fun getElementsList(): LiveData<List<Element>> = elementsList

    fun elementItemClicked(elementId: Int) {
        elementItemClickedData.value = elementId
    }
}
