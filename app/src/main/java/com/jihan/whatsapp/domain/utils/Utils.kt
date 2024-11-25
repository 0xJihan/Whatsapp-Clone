package com.jihan.whatsapp.domain.utils

import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    prettyPrint = true
    coerceInputValues = true
}