package com.kaniffoll.ifispinbot.service

import com.kaniffoll.ifispinbot.config.TelegramConfig
import com.kaniffoll.ifispinbot.consumer.UpdateConsumer
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot

@Component
class IfispinBot(
    private val telegramConfig: TelegramConfig,
    private val updateConsumer: UpdateConsumer,
) : SpringLongPollingBot {
    override fun getBotToken() = telegramConfig.token
    override fun getUpdatesConsumer() = updateConsumer
}
