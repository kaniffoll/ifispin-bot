package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.model.UserSession
import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class FindSourceAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService
): Action<Long, Unit> {
    override operator fun invoke(input: Long) {
        sessionService.setState(input, UserSession.WaitForSource)
        telegramClient.execute(SendMessage.builder()
            .text(ActionStringRes.FIND_SOURCE_MESSAGE)
            .chatId(input)
            .build()
        )
    }
}