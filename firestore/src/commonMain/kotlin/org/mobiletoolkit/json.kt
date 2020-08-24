package org.mobiletoolkit

import kotlinx.serialization.json.Json

/**
 * Created by Sebastian Owodzin on 23/08/2020
 */
fun json() = Json {
    ignoreUnknownKeys = true
    isLenient = true
}