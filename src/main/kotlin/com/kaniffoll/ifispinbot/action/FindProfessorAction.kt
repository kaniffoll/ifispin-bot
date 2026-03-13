package com.kaniffoll.ifispinbot.action

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class FindProfessorAction(private val telegramClient: TelegramClient): Action {
    override operator fun invoke(chatId: Long) {
        telegramClient.execute(SendMessage.builder()
            .text(ActionStringRes.FIND_PROFESSOR_MESSAGE)
            .chatId(chatId)
            .build()
        )
    }
}