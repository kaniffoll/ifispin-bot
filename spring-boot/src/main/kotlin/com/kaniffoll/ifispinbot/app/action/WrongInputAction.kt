package com.kaniffoll.ifispinbot.app.action

import com.kaniffoll.ifispinbot.app.action.utils.ActionUtils.actionButtonRow
import com.kaniffoll.ifispinbot.app.res.CallbackText
import com.kaniffoll.ifispinbot.data.utils.CallbackCodec
import com.kaniffoll.ifispinbot.domain.model.action.Action
import com.kaniffoll.ifispinbot.domain.model.action.WrongInput
import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.generics.TelegramClient

@Component
class WrongInputAction(
    private val telegramClient: TelegramClient,
) : Action<WrongInput, Unit> {
    override fun invoke(input: WrongInput) {
        telegramClient.execute(
            SendMessage.builder()
                .text(input.msg)
                .chatId(input.chatId)
                .build()
                .apply {
                    replyMarkup = InlineKeyboardMarkup(
                        listOf(
                            actionButtonRow(
                                CallbackText.STOP,
                                CallbackCodec.encode(CallbackAction.StopFindSource)
                            )
                        )
                    )
                }
        )
    }
}