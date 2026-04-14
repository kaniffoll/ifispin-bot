package com.kaniffoll.ifispinbot.app.action

import com.kaniffoll.ifispinbot.app.utils.SourceInputValidator
import com.kaniffoll.ifispinbot.app.action.utils.SourceKeyboardFactory
import com.kaniffoll.ifispinbot.app.utils.ValidatorResponse
import com.kaniffoll.ifispinbot.app.res.ActionStringRes
import com.kaniffoll.ifispinbot.domain.model.action.Action
import com.kaniffoll.ifispinbot.domain.model.action.GetSourcesInput
import com.kaniffoll.ifispinbot.domain.model.toTelegramMessageStringList
import com.kaniffoll.ifispinbot.domain.service.SessionService
import com.kaniffoll.ifispinbot.domain.service.WebService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class GetSourcesAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService,
    private val webService: WebService,
) : Action<GetSourcesInput, Unit> {
    override fun invoke(input: GetSourcesInput) {
        telegramClient.execute(
            SendMessage.builder()
                .text(ActionStringRes.GET_RESOURCES_WAITING_MESSAGE)
                .chatId(input.chatId)
                .build()
        )

        var isEnd = false
        sessionService.setQueryForSources(input.chatId, input.message)
        webService.getSources(query = input.message, page = input.page).subscribe { response ->
            if (response.meta.page >= response.meta.count / response.meta.perPage.toDouble()) {
                sessionService.clear(input.chatId)
                isEnd = true
            }

            response.toTelegramMessageStringList().let {
                it.forEachIndexed { index, msg ->
                    telegramClient.execute(
                        SendMessage.builder()
                            .text(msg)
                            .chatId(input.chatId)
                            .build()
                            .apply {
                                if (index == it.size - 1)
                                    replyMarkup =
                                        SourceKeyboardFactory.create(isEnd, input.page + 1)
                            }
                    )
                }
            }
        }
    }
}