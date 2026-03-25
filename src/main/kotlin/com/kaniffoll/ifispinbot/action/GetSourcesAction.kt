package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.action.ActionStringRes.NEXT_PAGE_TEXT
import com.kaniffoll.ifispinbot.action.model.GetSourcesInput
import com.kaniffoll.ifispinbot.model.GetSourcesCallbackData
import com.kaniffoll.ifispinbot.model.toTelegramMessageStringList
import com.kaniffoll.ifispinbot.service.SessionService
import com.kaniffoll.ifispinbot.service.WebService
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow
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
        webService.getSources(query = input.message, page = input.page).subscribe { response ->
            if (response.meta.page >= response.meta.count / response.meta.perPage.toDouble())
                sessionService.clear(input.chatId)

            val keyBoardRow = InlineKeyboardRow(
                InlineKeyboardButton.builder()
                    .text(NEXT_PAGE_TEXT)
                    .callbackData(
                        Json.encodeToString<GetSourcesCallbackData>(
                            GetSourcesCallbackData(
                                input.message,
                                input.page + 1
                            )
                        )
                    )
                    .build()
            )

            response.toTelegramMessageStringList().let {
                it.forEachIndexed { index, msg ->
                    telegramClient.execute(
                        SendMessage.builder()
                            .text(msg)
                            .chatId(input.chatId)
                            .build()
                            .apply {
                                if (index == it.size - 1)
                                    replyMarkup = InlineKeyboardMarkup(
                                        listOf(keyBoardRow)
                                    )
                            }
                    )
                }
            }
        }
    }
}