package com.kaniffoll.ifispinbot.handler

import com.kaniffoll.ifispinbot.action.GetSourcesAction
import com.kaniffoll.ifispinbot.action.GetTitleAction
import com.kaniffoll.ifispinbot.action.model.GetSourcesInput
import com.kaniffoll.ifispinbot.action.model.GetTitleInput
import com.kaniffoll.ifispinbot.model.UserSession
import org.springframework.stereotype.Component

@Component
class StateHandler(
    private val getSourcesAction: GetSourcesAction,
    private val getTitleAction: GetTitleAction
) {
    operator fun invoke(session: UserSession, text: String, chatId: Long) {
        when(session) {
            UserSession.WaitForProfessor -> getTitleAction(GetTitleInput(chatId, text))
            UserSession.WaitForSource -> getSourcesAction(GetSourcesInput(chatId, text))
        }
    }
}