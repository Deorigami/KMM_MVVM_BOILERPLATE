

package dev.shared.data.web.mapper

import dev.shared.base.BaseRespondDto
import dev.shared.base.BaseRespondDto.Companion.toBaseRespondEntity
import dev.shared.base.BaseRespondEntity
import dev.shared.data.web.dto.ProductItemDto
import dev.shared.domain.entity.ProductItemEntity

class ProductItemsDtoMapper {
    operator fun invoke(from : BaseRespondDto<List<ProductItemDto>>) : BaseRespondEntity<List<ProductItemEntity>> {
        return from.toBaseRespondEntity(from.data.map {
            ProductItemEntity(
                image = it.image,
                price = it.price,
                rating = it.rating,
                reviewsCount = it.reviewsCount,
                title = it.title,
            )
        })
    }
}