

package dev.shared.domain.repository

import dev.shared.base.BaseRespondEntity
import dev.shared.domain.entity.ProductCategoryItemEntity

interface EcommerceRepository {
    suspend fun getProductCategory() : BaseRespondEntity<List<ProductCategoryItemEntity>>
}