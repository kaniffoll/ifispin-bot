package com.kaniffoll.ifispinbot.app.handler

import com.kaniffoll.ifispinbot.app.action.StartAction
import com.kaniffoll.ifispinbot.app.action.WrongReqAction
import com.kaniffoll.ifispinbot.domain.model.upd.CommandType
import org.springframework.stereotype.Component

@Component
class CommandHandler(
    private val startAction: StartAction,
    private val wrongReqAction: WrongReqAction,
) {

    operator fun invoke(text: String, chatId: Long) {
        if (text !in CommandType.valuesCommands) {
            wrongReqAction(chatId)
            return
        }

        when (CommandType.from(text)) {
            CommandType.Start -> startAction(chatId)
        }
    }
}