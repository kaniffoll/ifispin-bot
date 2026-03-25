package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.FindProfessorAction
import com.kaniffoll.ifispinbot.action.FindSourceAction
import com.kaniffoll.ifispinbot.action.GetSourcesAction
import com.kaniffoll.ifispinbot.action.StopFindSourceAction
import com.kaniffoll.ifispinbot.action.model.GetSourcesInput
import com.kaniffoll.ifispinbot.model.CallbackQuery
import com.kaniffoll.ifispinbot.service.SessionService
import org.springframework.stereotype.Component

@Component
class CallbackQueryHandler(
    private val sessionService: SessionService,
    private val findProfessorAction: FindProfessorAction,
    private val stopFindSourceAction: StopFindSourceAction,
    private val findSourceAction: FindSourceAction,
    private val getSourcesAction: GetSourcesAction
) {
    operator fun invoke(data: String, chatId: Long) {
        when (CallbackQuery.from(data)) {
            CallbackQuery.FindSource -> findSourceAction(chatId)
            CallbackQuery.FindProfessor -> findProfessorAction(chatId)
            CallbackQuery.StopFindSource -> stopFindSourceAction(chatId)
            is CallbackQuery.GetNextPage -> {
                getSourcesAction(
                    GetSourcesInput(
                        chatId,
                        sessionService.getQueryForSources(chatId)!!,
                        data.toInt()
                    )
                )
            }
        }
    }
}