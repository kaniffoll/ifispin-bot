package com.kaniffoll.ifispinbot.app.action

import com.kaniffoll.ifispinbot.app.action.utils.ActionUtils.actionButtonRow
import com.kaniffoll.ifispinbot.app.res.ActionStringRes
import com.kaniffoll.ifispinbot.app.res.CallbackText.FIND_TEXTS_MAP
import com.kaniffoll.ifispinbot.data.utils.CallbackCodec
import com.kaniffoll.ifispinbot.domain.model.action.Action
import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class StartAction(private val telegramClient: TelegramClient) : Action<Long, Unit> {
    override operator fun invoke(input: Long) {
        val keyboardButtonsRows = listOf(CallbackAction.FindProfessor, CallbackAction.FindSource).map {
            actionButtonRow(FIND_TEXTS_MAP[it]!!, CallbackCodec.encode(it))
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