

package dev.shared.data.web.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SectionPagesDto(
    @SerialName("banner")
    val banner: String,
    @SerialName("banner_lg")
    val bannerLg: String,
    @SerialName("banner_md")
    val bannerMd: String,
    @SerialName("title_a")
    val titleA: String,
    @SerialName("title_b")
    val titleB: String,
    @SerialName("title_c")
    val titleC: String
)