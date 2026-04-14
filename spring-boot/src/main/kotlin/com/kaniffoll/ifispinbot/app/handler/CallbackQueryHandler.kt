package com.kaniffoll.ifispinbot.app.handler

import com.kaniffoll.ifispinbot.app.action.FindProfessorAction
import com.kaniffoll.ifispinbot.app.action.FindSourceAction
import com.kaniffoll.ifispinbot.app.action.GetSourcesAction
import com.kaniffoll.ifispinbot.app.action.StopFindSourceAction
import com.kaniffoll.ifispinbot.data.utils.CallbackCodec
import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction
import com.kaniffoll.ifispinbot.domain.model.action.GetSourcesInput
import com.kaniffoll.ifispinbot.domain.service.SessionService
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
        when (val action = CallbackCodec.decode(data)) {
            CallbackAction.FindProfessor -> findProfessorAction(chatId)
            CallbackAction.FindSource -> findSourceAction(chatId)
            CallbackAction.StopFindSource -> stopFindSourceAction(chatId)
            is CallbackAction.GetNextPage -> {
                getSourcesAction(
                    GetSourcesInput(
                        chatId,
                        sessionService.getQueryForSources(chatId)!!,
                        action.page
                    )
                )
            }
        }
    }
}