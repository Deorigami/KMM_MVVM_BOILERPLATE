

package dev.shared.data.web.dto
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class ProductCategoryItemDto(
    @SerialName("category")
    val category: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("backgroundColor")
    val backgroundColor: String
)