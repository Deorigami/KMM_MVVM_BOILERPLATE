import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class AppContext(val context: Context)

actual class KtorClientEngine constructor(private val appContext: AppContext) {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(OkHttp) {
        config.invoke(this)
        engine {
            val chucker = ChuckerInterceptor.Builder(appContext.context).build()
            addInterceptor(chucker)
        }
    }
    actual companion object Factory {
        lateinit var context: Context
        actual fun build(): KtorClientEngine = KtorClientEngine(AppContext(context))
    }
}