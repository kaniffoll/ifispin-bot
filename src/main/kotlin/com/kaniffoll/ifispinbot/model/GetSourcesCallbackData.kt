package com.kaniffoll.ifispinbot.model

import kotlinx.serialization.Serializable

@Serializable
data class GetSourcesCallbackData(
    val message: String,
    val page: Int
)

