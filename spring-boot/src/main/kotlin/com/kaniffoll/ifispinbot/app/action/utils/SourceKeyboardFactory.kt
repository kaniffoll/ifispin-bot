package com.kaniffoll.ifispinbot.app.action.utils

import com.kaniffoll.ifispinbot.app.action.utils.ActionUtils.actionButtonRow
import com.kaniffoll.ifispinbot.app.res.CallbackText
import com.kaniffoll.ifispinbot.data.utils.CallbackCodec
import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

object SourceKeyboardFactory {
    fun create(isEnd: Boolean, nextPage: Int): InlineKeyboardMarkup {
        val stop = actionButtonRow(
            CallbackText.STOP,
            CallbackCodec.encode(CallbackAction.StopFindSource)
        )

        if (isEnd) {
            return InlineKeyboardMarkup(listOf(stop))
        }

        val next = actionButtonRow(
            CallbackText.NEXT,
            CallbackCodec.encode(CallbackAction.GetNextPage(nextPage))
        )

        return InlineKeyboardMarkup(listOf(next, stop))
    }
}