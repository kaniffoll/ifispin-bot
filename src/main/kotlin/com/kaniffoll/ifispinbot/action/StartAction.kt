package com.kaniffoll.ifispinbot.action

import com.kaniffoll.ifispinbot.model.CallbackQuery
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class StartAction(private val telegramClient: TelegramClient) : Action<Long, Unit> {
    override operator fun invoke(input: Long) {
        val keyboardButtonsRows = CallbackQuery.findValues.map {
            InlineKeyboardRow(
                InlineKeyboardButton.builder()
                    .text(it.text)
                    .callbackData(it.data)
                    .build()
            )
        }

        telegramClient.execute(
            SendMessage.builder()
                .text(ActionStringRes.START_MESSAGE)
                .chatId(input)
                .build()
                .apply { replyMarkup = InlineKeyboardMarkup(keyboardButtonsRows) }
        )
    }

}