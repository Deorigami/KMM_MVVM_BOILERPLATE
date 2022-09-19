package dev.shared.viewmodels

import dev.shared.base.StatefulData
import dev.shared.domain.usecase.SectionPagesUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.shared.domain.usecase.GetProductCategoryUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SectionPageViewModel : ViewModel(), KoinComponent {
    private val getProductCategoryUseCase by inject<GetProductCategoryUseCase>()

    val productCategory = StatefulData(
        getProductCategoryUseCase,
        viewModelScope
    )

    init {
        productCategory.loadData(Unit)
    }
}