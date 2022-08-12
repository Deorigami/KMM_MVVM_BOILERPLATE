/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.di

import dev.shared.data.web.mapper.SectionPagesMapper
import dev.shared.data.web.repository.ExampleRepositoryImpl
import dev.shared.data.web.service.ExampleService
import dev.shared.domain.usecase.SectionPagesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule() = listOf(
    serviceModule(),
    mapperModule(),
    repositoryModule(),
    useCaseModule()
)


fun serviceModule() = module {
    singleOf(::ExampleService)
}

fun mapperModule() = module {
    singleOf(::SectionPagesMapper)
}

fun repositoryModule() = module {
    singleOf(::ExampleRepositoryImpl)
}

fun useCaseModule() = module {
    singleOf(::SectionPagesUseCase)
}