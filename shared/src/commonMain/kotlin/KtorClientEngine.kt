import io.ktor.client.*
import org.koin.dsl.module


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class KtorClientEngine {
    fun getClientEngine(baseUrl : String = "https://ardinata.tech",config: HttpClientConfig<*>.() -> Unit) : HttpClient
    companion object Factory {
        fun build() : KtorClientEngine
    }
}