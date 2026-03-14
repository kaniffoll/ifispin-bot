package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.StartAction
import com.kaniffoll.ifispinbot.action.WrongReqAction
import com.kaniffoll.ifispinbot.model.CommandType
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