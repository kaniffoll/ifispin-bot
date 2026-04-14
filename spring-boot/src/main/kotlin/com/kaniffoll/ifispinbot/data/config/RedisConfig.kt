package com.kaniffoll.ifispinbot.data.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("redis")
data class RedisConfig(
    val host: String,
    val port: Int,
)
