package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.FindProfessorAction
import com.kaniffoll.ifispinbot.action.FindSourceAction
import com.kaniffoll.ifispinbot.model.CallbackQuery
import org.springframework.stereotype.Component

@Component
class CallbackQueryHandler(
    private val findProfessorAction: FindProfessorAction,
    private val findSourceAction: FindSourceAction
) {

    operator fun invoke(data: String, chatId: Long): Unit? {
        if (data !in CallbackQuery.valuesData)
            return null

        when(CallbackQuery.from(data)) {
            CallbackQuery.FindSourceQuery -> findSourceAction(chatId)
            CallbackQuery.FindProfessorQuery -> findProfessorAction(chatId)
        }
        return Unit
    }
}