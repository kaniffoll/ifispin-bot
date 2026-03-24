package com.kaniffoll.ifispinbot.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api")
data class ApiConfig(
    val url: String,
    val path: String,
    val apiKey: String,
    val apiKeyParam: String,
    val searchParam: String,
    val filterParam: String,
    val selectParam: String,
    val pageParam: String,
    val perPageParam: String,
)
