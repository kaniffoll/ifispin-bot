package com.kaniffoll.ifispinbot.data.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("fast-api")
data class FastApiConfig(
    val host: String,
    val port: String
)
