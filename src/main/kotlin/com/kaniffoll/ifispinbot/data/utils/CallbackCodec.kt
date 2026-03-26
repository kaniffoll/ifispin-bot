package com.kaniffoll.ifispinbot.data.utils

import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction

object CallbackCodec {
    private const val FIND_PROFESSOR = "prof"
    private const val FIND_SOURCE = "src"
    private const val STOP = "stop"
    private const val NEXT = "next"

    fun encode(action: CallbackAction): String {
        return when (action) {
            CallbackAction.FindProfessor -> FIND_PROFESSOR
            CallbackAction.FindSource -> FIND_SOURCE
            is CallbackAction.GetNextPage -> "$NEXT:${action.page}"
            CallbackAction.StopFindSource -> STOP
        }
    }

    fun decode(data: String): CallbackAction {
        return when {
            data == FIND_PROFESSOR -> CallbackAction.FindProfessor
            data == FIND_SOURCE -> CallbackAction.FindSource
            data == STOP -> CallbackAction.StopFindSource
            data.startsWith("$NEXT:") -> {
                val page = data.removePrefix("$NEXT:").toInt()
                CallbackAction.GetNextPage(page)
            }
            else -> error("Unknown callback: $data")
        }
    }
}