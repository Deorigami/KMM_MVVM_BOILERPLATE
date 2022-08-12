/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.viewmodels

import dev.shared.base.StatefulData
import dev.shared.domain.usecase.SectionPagesUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SectionPageViewModel : ViewModel(), KoinComponent {
    private val sectionPageUseCase by inject<SectionPagesUseCase>()
    val sectionPageData = StatefulData(
        sectionPageUseCase,
        viewModelScope
    )

    init {
        sectionPageData.loadData(Unit)
    }
}