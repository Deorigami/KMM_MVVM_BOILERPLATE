

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
    }
}

suspend inline fun <reified T> clientGet(
    url: String,
    requestBuilder: (HttpRequestBuilder) -> Unit = {}
) : BaseRespondDto<T> = KtorNetworkProvider.client.get{
    url(url)
    requestBuilder.invoke(this)
}.body()