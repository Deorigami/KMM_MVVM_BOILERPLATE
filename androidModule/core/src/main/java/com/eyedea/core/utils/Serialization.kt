package com.eyedea.core.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> deserialize(str: String): T {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    return json.decodeFromString(str)
}