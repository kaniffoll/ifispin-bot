package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.model.toTelegramMessageStringList
import com.kaniffoll.ifispinbot.service.SessionService
import com.kaniffoll.ifispinbot.service.WebService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class GetSourcesAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService,
    private val webService: WebService,
) : MessageAction {
    override fun invoke(chatId: Long, message: String) {
        sessionService.clear(chatId)
        telegramClient.execute(
            SendMessage.builder()
                .text(ActionStringRes.GET_RESOURCES_WAITING_MESSAGE)
                .chatId(chatId)
                .build()
        )
        webService.getSources(message).subscribe { response ->
            response.toTelegramMessageStringList().forEach {
                telegramClient.execute(
                    SendMessage.builder()
                        .text(it)
                        .chatId(chatId)
                        .build()
                )
            }
        }
    }
}