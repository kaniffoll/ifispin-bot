package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.service.SessionService
import com.kaniffoll.ifispinbot.service.WebService
import com.kaniffoll.ifispinbot.utils.toTelegramMessageStringList
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
        webService.getSources(message).subscribe { response ->
            response.data.toTelegramMessageStringList().forEach {
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