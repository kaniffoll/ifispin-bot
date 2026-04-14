package com.kaniffoll.ifispinbot.data.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.RedisClient

@Configuration
class JedisConfiguration(private val redisConfig: RedisConfig) {
    @Bean
    fun jedisClient(): RedisClient =
        RedisClient.builder().hostAndPort(redisConfig.host, redisConfig.port).build()
}