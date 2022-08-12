/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.data.web.service

import dev.shared.base.BaseRespondDto
import dev.shared.base.clientGet
import dev.shared.data.web.dto.SectionPagesDto

class ExampleService {
    suspend fun getSectionPages() : BaseRespondDto<SectionPagesDto> {
        return clientGet("v2/packages/content")
    }
}