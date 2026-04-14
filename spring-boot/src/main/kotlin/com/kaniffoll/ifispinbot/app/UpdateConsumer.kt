package com.kaniffoll.ifispinbot.app

import com.kaniffoll.ifispinbot.app.UpdateRouter
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UpdateConsumer(
    private val updateRouter: UpdateRouter
) : LongPollingSingleThreadUpdateConsumer {

    override fun consume(upd: Update) {
        updateRouter(upd)
    }
}