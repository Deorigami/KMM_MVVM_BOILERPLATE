import io.ktor.client.*
import org.koin.dsl.module

/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class KtorClientEngine {
    fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient
    companion object Factory {
        fun build() : KtorClientEngine
    }
}