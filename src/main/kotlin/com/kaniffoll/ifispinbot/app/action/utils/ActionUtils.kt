package com.kaniffoll.ifispinbot.app.action.utils

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow

object ActionUtils {
    fun actionButtonRow(text: String, data: String) =
        InlineKeyboardRow(
            InlineKeyboardButton.builder()
                .text(text)
                .callbackData(data)
                .build()
        )
}