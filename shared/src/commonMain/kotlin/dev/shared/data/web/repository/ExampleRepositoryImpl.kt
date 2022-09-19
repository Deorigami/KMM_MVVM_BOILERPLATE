

package dev.shared.data.web.repository

import dev.shared.base.BaseRespondEntity
import dev.shared.data.web.mapper.SectionPagesMapper
import dev.shared.data.web.service.ExampleService
import dev.shared.domain.entity.SectionPagesEntity
import dev.shared.domain.repository.ExampleRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExampleRepositoryImpl : ExampleRepository, KoinComponent {
    private val exampleService by inject<ExampleService>()
    private val sectionPagesMapper by inject<SectionPagesMapper>()
    override suspend fun getSectionPages(): BaseRespondEntity<SectionPagesEntity> {
        return sectionPagesMapper.invoke(exampleService.getSectionPages())
    }
}