package dev.shared.data.web.service

import dev.shared.base.BaseRespondDto
import dev.shared.base.clientGet
import dev.shared.data.web.dto.ProductCategoryItemDto
import io.ktor.client.utils.*

class EcommerceService {
    suspend fun getProductCategories() : BaseRespondDto<List<ProductCategoryItemDto>> = clientGet("categories")
}