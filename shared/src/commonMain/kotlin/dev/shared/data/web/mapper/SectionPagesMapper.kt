

package dev.shared.data.web.mapper

import dev.shared.base.BaseRespondEntity
import dev.shared.base.BaseRespondDto
import dev.shared.base.BaseRespondDto.Companion.toBaseRespondEntity
import dev.shared.data.web.dto.SectionPagesDto
import dev.shared.domain.entity.SectionPagesEntity

class SectionPagesMapper {
    operator fun invoke(from : BaseRespondDto<SectionPagesDto>) : BaseRespondEntity<SectionPagesEntity> {
        return from.toBaseRespondEntity(from.data.let {
            SectionPagesEntity(
                banner = it.banner,
                bannerLg = it.bannerLg,
                bannerMd = it.bannerMd,
                titleA = it.titleA,
                titleB = it.titleB,
                titleC = it.titleC,
            )
        })
    }
}