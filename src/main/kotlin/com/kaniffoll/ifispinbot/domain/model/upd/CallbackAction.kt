package com.kaniffoll.ifispinbot.domain.model.upd

sealed interface CallbackAction {
    data object FindProfessor : CallbackAction
    data object FindSource: CallbackAction
    data object StopFindSource: CallbackAction
    data class GetNextPage(val page: Int): CallbackAction
}