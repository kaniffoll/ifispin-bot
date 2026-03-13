package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.StartAction
import com.kaniffoll.ifispinbot.config.TelegramConfig
import com.kaniffoll.ifispinbot.model.CommandType
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient

@Component
class CommandHandler(private val startAction: StartAction) {

    operator fun invoke(text: String, chatId: Long): Unit? {
        if (text !in CommandType.valuesCommands)
            return null

        when (CommandType.from(text)) {
            CommandType.Help -> {
                println("HELP COMMAND: $text")
            }
            CommandType.Start -> startAction(chatId)
        }
        return Unit
    }
}