package com.kaniffoll.ifispinbot.consumer

import com.kaniffoll.ifispinbot.handler.CallbackQueryHandler
import com.kaniffoll.ifispinbot.handler.CommandHandler
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UpdateConsumer(
    private val commandHandler: CommandHandler,
    private val callbackQueryHandler: CallbackQueryHandler
) : LongPollingSingleThreadUpdateConsumer {

    override fun consume(upd: Update) {
        when {
            upd.hasMessage() -> {
                commandHandler(upd.message.text, upd.message.chatId)
                    ?: println("${upd.message.text} from ${upd.message.chatId}")
            }
            upd.hasCallbackQuery() -> {
                callbackQueryHandler(upd.callbackQuery.data, upd.callbackQuery.message.chatId)
                    ?: println("${upd.callbackQuery.data} from ${upd.callbackQuery.message.chatId}")
            }
        }
    }
}