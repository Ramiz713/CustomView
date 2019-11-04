package com.task.customview.di

import com.task.customview.details.DetailsViewModel
import com.task.customview.list.ListViewModel
import com.task.customview.model.interactors.ElementsInteractor
import com.task.customview.model.interactors.IElementsInteractor
import com.task.customview.model.repository.ElementsRepository
import com.task.customview.model.repository.IElementsRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<IElementsRepository> { ElementsRepository() }

    single<IElementsInteractor> { ElementsInteractor(get()) }

    viewModel { ListViewModel(get()) }

    viewModel { DetailsViewModel(get()) }
}
