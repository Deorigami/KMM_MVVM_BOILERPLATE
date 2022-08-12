/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.domain.repository

import dev.shared.base.BaseRespondEntity
import dev.shared.domain.entity.SectionPagesEntity

interface ExampleRepository {
    suspend fun getSectionPages() : BaseRespondEntity<SectionPagesEntity>
}