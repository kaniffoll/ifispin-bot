package com.kaniffoll.ifispinbot.consumer

import com.kaniffoll.ifispinbot.router.UpdateRouter
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