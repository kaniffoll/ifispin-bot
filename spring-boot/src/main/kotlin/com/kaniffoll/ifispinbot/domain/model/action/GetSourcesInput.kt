package com.kaniffoll.ifispinbot.domain.model.action

data class GetSourcesInput(
    val chatId: Long,
    val message: String,
    val page: Int = 1,
)
