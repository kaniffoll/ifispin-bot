package com.kaniffoll.ifispinbot.app.res

import com.kaniffoll.ifispinbot.domain.model.upd.CallbackAction

object CallbackText {
    const val FIND_PROFESSOR = "Поиск руководителя"
    const val FIND_SOURCE = "Поиск литературы"
    const val STOP = "Остановить поиск"
    const val NEXT = "Следующая страница"

    val FIND_TEXTS_MAP = mapOf(
        CallbackAction.FindProfessor to FIND_PROFESSOR,
        CallbackAction.FindSource to FIND_SOURCE
    )
}