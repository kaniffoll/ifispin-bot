package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.action.model.GetTitleInput
import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class GetTitleAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService
) : Action<GetTitleInput, Unit> {
    override operator fun invoke(input: GetTitleInput) {
        sessionService.clear(input.chatId)
        telegramClient.execute(
            SendMessage.builder()
                .text(input.message)
                .chatId(input.chatId)
                .build()
        )
    }
}