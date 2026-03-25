package com.kaniffoll.ifispinbot.action

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class WrongReqAction(private val telegramClient: TelegramClient): Action<Long, Unit> {
    override operator fun invoke(input: Long) {
        telegramClient.execute(
            SendMessage.builder()
                .chatId(input)
                .text(ActionStringRes.WRONG_REQ_MESSAGE)
                .build()
        )
    }
}