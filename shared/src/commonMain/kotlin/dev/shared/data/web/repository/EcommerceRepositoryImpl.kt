

package dev.shared.data.web.repository

import dev.shared.base.BaseRespondEntity
import dev.shared.data.web.mapper.ProductCategoryDtoMapper
import dev.shared.data.web.service.EcommerceService
import dev.shared.domain.entity.ProductCategoryItemEntity
import dev.shared.domain.repository.EcommerceRepository

class EcommerceRepositoryImpl : EcommerceRepository {
    private val service = EcommerceService()
    override suspend fun getProductCategory(): BaseRespondEntity<List<ProductCategoryItemEntity>> {
        return ProductCategoryDtoMapper().invoke(service.getProductCategories())
    }
}