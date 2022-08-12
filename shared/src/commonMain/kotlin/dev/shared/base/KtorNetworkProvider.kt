/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.base

import KtorClientEngine
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorNetworkProvider {
    val client = KtorClientEngine.build().getClientEngine {
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url {
                host = "bd638bf2-e780-42b9-8226-6413d82bdfa8.mock.pstmn.io"
                protocol = URLProtocol.HTTPS
            }
        }
    }
}

suspend inline fun <reified T> clientGet(url: String) : BaseRespondDto<T> = KtorNetworkProvider.client.get(url).body()