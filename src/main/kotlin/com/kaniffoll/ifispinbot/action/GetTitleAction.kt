package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class GetTitleAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService
) : MessageAction {
    override fun invoke(chatId: Long, message: String) {
        sessionService.clear(chatId)
        telegramClient.execute(
            SendMessage.builder()
                .text(message)
                .chatId(chatId)
                .build()
        )
    }
}