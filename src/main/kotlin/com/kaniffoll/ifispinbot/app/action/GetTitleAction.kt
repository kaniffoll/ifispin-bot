package com.kaniffoll.ifispinbot.app.action

import com.kaniffoll.ifispinbot.domain.model.action.GetTitleInput
import com.kaniffoll.ifispinbot.domain.model.action.Action
import com.kaniffoll.ifispinbot.domain.service.SessionService
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