package com.kaniffoll.ifispinbot.router

import com.kaniffoll.ifispinbot.handler.CallbackQueryHandler
import com.kaniffoll.ifispinbot.handler.CommandHandler
import com.kaniffoll.ifispinbot.handler.StateHandler
import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UpdateRouter(
    private val callbackQueryHandler: CallbackQueryHandler,
    private val commandHandler: CommandHandler,
    private val sessionService: SessionService,
    private val stateHandler: StateHandler
) {
    operator fun invoke(upd: Update) {

        when {
            upd.hasMessage() -> {
                val chatId = upd.message.chatId
                val messageText = upd.message.text
                val session = sessionService.getUserSession(chatId)
                if (session == null)
                    commandHandler(messageText, chatId)
                else
                    stateHandler(session, messageText, chatId)
            }

            upd.hasCallbackQuery() -> {
                callbackQueryHandler(upd.callbackQuery.data, upd.callbackQuery.message.chatId)
            }
        }
    }
}