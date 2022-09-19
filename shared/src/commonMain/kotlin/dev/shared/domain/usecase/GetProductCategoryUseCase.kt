

package dev.shared.domain.usecase

import dev.shared.base.BaseRespondEntity
import dev.shared.base.BaseUseCase
import dev.shared.data.web.repository.EcommerceRepositoryImpl
import dev.shared.domain.entity.ProductCategoryItemEntity
import dev.shared.domain.repository.EcommerceRepository

class GetProductCategoryUseCase : BaseUseCase<Unit, List<ProductCategoryItemEntity>>(){

    private val repo : EcommerceRepository = EcommerceRepositoryImpl()

    override val default: List<ProductCategoryItemEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): BaseRespondEntity<List<ProductCategoryItemEntity>> {
        return repo.getProductCategory()
    }
}