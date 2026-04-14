package com.kaniffoll.ifispinbot.app.handler

import com.kaniffoll.ifispinbot.app.action.GetSourcesAction
import com.kaniffoll.ifispinbot.app.action.GetTitleAction
import com.kaniffoll.ifispinbot.app.action.WrongInputAction
import com.kaniffoll.ifispinbot.app.utils.SourceInputValidator
import com.kaniffoll.ifispinbot.app.utils.ValidatorResponse
import com.kaniffoll.ifispinbot.domain.model.action.GetSourcesInput
import com.kaniffoll.ifispinbot.domain.model.action.GetTitleInput
import com.kaniffoll.ifispinbot.domain.model.UserSession
import com.kaniffoll.ifispinbot.domain.model.action.WrongInput
import org.springframework.stereotype.Component

@Component
class StateHandler(
    private val getSourcesAction: GetSourcesAction,
    private val getTitleAction: GetTitleAction,
    private val wrongInputAction: WrongInputAction,
) {
    operator fun invoke(session: UserSession, text: String, chatId: Long) {
        when(session) {
            UserSession.WaitForProfessor -> 
                getTitleAction(GetTitleInput(chatId, text))

            UserSession.WaitForSource -> {
                when (val res = SourceInputValidator.validateInput(text)) {
                    ValidatorResponse.OK ->
                        getSourcesAction(GetSourcesInput(chatId, text))

                    is ValidatorResponse.WrongInput ->
                        wrongInputAction(WrongInput(res.msg, chatId))
                }
            }
        }
    }
}
