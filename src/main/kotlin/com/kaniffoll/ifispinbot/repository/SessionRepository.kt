package com.kaniffoll.ifispinbot.repository

import org.springframework.stereotype.Repository
import redis.clients.jedis.RedisClient
import redis.clients.jedis.params.SetParams

@Repository
class SessionRepository(private val jedisClient: RedisClient) {
    fun getSessionId(chatId: String): Int? =
        jedisClient.get(chatId)?.toInt()

    fun setSessionId(chatId: String, id: String) {
        jedisClient.set(
            chatId,
            id,
            SetParams.setParams().ex(TTL)
        )
    }

    fun clear(chatId: String) {
        jedisClient.del(chatId)
    }

    companion object {
        private const val TTL = 1800L
    }
}