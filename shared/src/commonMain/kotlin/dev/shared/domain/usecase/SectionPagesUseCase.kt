/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.domain.usecase

import dev.shared.base.BaseRespondEntity
import dev.shared.base.BaseUseCase
import dev.shared.base.KtorNetworkProvider
import dev.shared.data.web.mapper.SectionPagesMapper
import dev.shared.data.web.repository.ExampleRepositoryImpl
import dev.shared.domain.entity.SectionPagesEntity
import dev.shared.domain.repository.ExampleRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SectionPagesUseCase : BaseUseCase<Unit, SectionPagesEntity>(), KoinComponent {
    private val exampleRepository : ExampleRepository by inject<ExampleRepositoryImpl>()
    override val default: SectionPagesEntity
        get() = SectionPagesEntity(
            "",
            "",
            "",
            "",
            "",
            "",
        )

    override suspend fun build(param: Unit): BaseRespondEntity<SectionPagesEntity> {
        return exampleRepository.getSectionPages()
    }
}