package com.kaniffoll.ifispinbot.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api")
data class ApiConfig(
    val url: String,
    val fields: String,
    val searchPath: String,
)
