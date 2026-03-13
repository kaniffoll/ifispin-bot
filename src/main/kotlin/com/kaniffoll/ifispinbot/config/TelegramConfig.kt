package com.kaniffoll.ifispinbot.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("telegram")
data class TelegramConfig(
    val botName: String,
    val token: String,
)