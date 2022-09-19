

package dev.shared.domain.repository

import dev.shared.base.BaseRespondEntity
import dev.shared.domain.entity.SectionPagesEntity

interface ExampleRepository {
    suspend fun getSectionPages() : BaseRespondEntity<SectionPagesEntity>
}