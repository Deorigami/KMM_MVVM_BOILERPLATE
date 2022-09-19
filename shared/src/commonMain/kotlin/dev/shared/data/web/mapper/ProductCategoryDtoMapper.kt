

package dev.shared.data.web.mapper

import dev.shared.base.BaseRespondDto
import dev.shared.base.BaseRespondDto.Companion.toBaseRespondEntity
import dev.shared.base.BaseRespondEntity
import dev.shared.data.web.dto.ProductCategoryItemDto
import dev.shared.domain.entity.ProductCategoryItemEntity

class ProductCategoryDtoMapper {
    operator fun invoke(from : BaseRespondDto<List<ProductCategoryItemDto>>) : BaseRespondEntity<List<ProductCategoryItemEntity>> {
        return from.toBaseRespondEntity(from.data.map { ProductCategoryItemEntity(
            it.category,
            it.imageUrl,
            it.backgroundColor
        ) })
    }
}