package com.kaniffoll.ifispinbot.action.model

data class GetSourcesInput(
    val chatId: Long,
    val message: String,
    val page: Int = 1,
)
