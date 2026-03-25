package com.kaniffoll.ifispinbot.model

import kotlinx.serialization.json.Json

sealed class ParsedResult {
    data class JsonData(val value: GetSourcesCallbackData): ParsedResult()
    data class RowData(val value: String): ParsedResult()

    companion object {
        fun parse(input: String): ParsedResult {
            return try {
                val parsed: GetSourcesCallbackData = Json.decodeFromString(input)
                JsonData(parsed)
            } catch (_: Exception) {
                RowData(input)
            }
        }
    }
}