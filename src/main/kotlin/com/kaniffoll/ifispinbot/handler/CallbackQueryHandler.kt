package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.FindProfessorAction
import com.kaniffoll.ifispinbot.action.FindSourceAction
import com.kaniffoll.ifispinbot.action.GetSourcesAction
import com.kaniffoll.ifispinbot.action.model.GetSourcesInput
import com.kaniffoll.ifispinbot.model.CallbackQuery
import com.kaniffoll.ifispinbot.model.ParsedResult
import org.springframework.stereotype.Component

@Component
class CallbackQueryHandler(
    private val findProfessorAction: FindProfessorAction,
    private val findSourceAction: FindSourceAction,
    private val getSourcesAction: GetSourcesAction
) {

    operator fun invoke(data: String, chatId: Long) {
        when(val callbackQuery = CallbackQuery.from(data)) {
            CallbackQuery.FindSource -> findSourceAction(chatId)
            CallbackQuery.FindProfessor -> findProfessorAction(chatId)
            is CallbackQuery.GetNextPage ->
                getSourcesAction(
                    GetSourcesInput(
                        chatId,
                        callbackQuery.data.message,
                        callbackQuery.data.page,
                    )
                )
        }
    }
}