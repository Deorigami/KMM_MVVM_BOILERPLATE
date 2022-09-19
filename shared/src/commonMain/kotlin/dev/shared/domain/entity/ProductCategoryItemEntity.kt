

package dev.shared.domain.entity

data class ProductCategoryItemEntity(
    val category: String,
    val imageUrl: String,
    val backgroundColor: String
) {
    companion object {
        val DEFAULT = ProductCategoryItemEntity(
            "",
            "",
            "",
        )
    }
}