package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.model.UserSession
import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class FindProfessorAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService
): Action {
    override operator fun invoke(chatId: Long) {
        sessionService.setState(chatId, UserSession.WaitForProfessor)
        telegramClient.execute(SendMessage.builder()
            .text(ActionStringRes.FIND_PROFESSOR_MESSAGE)
            .chatId(chatId)
            .build()
        )
    }
}