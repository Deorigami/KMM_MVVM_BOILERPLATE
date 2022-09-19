

package dev.shared.data.web.dto
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class ProductItemDto(
    @SerialName("image")
    val image: String,
    @SerialName("price")
    val price: String,
    @SerialName("rating")
    val rating: String,
    @SerialName("reviews_count")
    val reviewsCount: String,
    @SerialName("title")
    val title: String
)