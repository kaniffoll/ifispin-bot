package com.kaniffoll.ifispinbot.app.action

import com.kaniffoll.ifispinbot.domain.model.action.GetTitleInput
import com.kaniffoll.ifispinbot.domain.model.action.Action
import com.kaniffoll.ifispinbot.domain.service.SessionService
import com.kaniffoll.ifispinbot.domain.service.FastApiService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.generics.TelegramClient
import com.kaniffoll.ifispinbot.app.res.ActionStringRes


@Component
class GetTitleAction(
    private val telegramClient: TelegramClient,
    private val sessionService: SessionService,
    private val fastApiService: FastApiService
) : Action<GetTitleInput, Unit> {
    override operator fun invoke(input: GetTitleInput) {
        sessionService.clear(input.chatId)
        
        telegramClient.execute(
            SendMessage.builder()
                .text(ActionStringRes.GET_RESOURCES_WAITING_MESSAGE)
                .chatId(input.chatId)
                .build()
        )

        fastApiService.getProfessors(input.message).subscribe { response ->
            telegramClient.execute( 
                SendMessage.builder()
                    .text(response.toString())
                    .chatId(input.chatId)
                    .build()
            )
        }

   }
}
