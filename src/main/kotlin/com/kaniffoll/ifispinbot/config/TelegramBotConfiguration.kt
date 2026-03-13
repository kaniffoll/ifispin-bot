package com.kaniffoll.ifispinbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.generics.TelegramClient

@Configuration
class TelegramBotConfiguration(private val telegramConfig: TelegramConfig) {
    @Bean
    fun telegramClient(): TelegramClient = OkHttpTelegramClient(telegramConfig.token)
}