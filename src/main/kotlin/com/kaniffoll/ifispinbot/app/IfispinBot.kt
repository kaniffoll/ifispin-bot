package com.kaniffoll.ifispinbot.app

import com.kaniffoll.ifispinbot.data.config.TelegramConfig
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