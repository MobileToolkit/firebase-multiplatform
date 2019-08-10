package org.mobiletoolkit.extensions

/**
 * Created by Sebastian Owodzin on 10/08/2019.
 */
fun Map<*, *>.toJsonString() = "{${map { "\"${it.key}\": \"${it.value}\"" }.joinToString(", ")}}"