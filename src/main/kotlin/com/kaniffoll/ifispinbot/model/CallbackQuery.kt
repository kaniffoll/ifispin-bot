package com.kaniffoll.ifispinbot.model

sealed class CallbackQuery(open val data: String, val text: String) {
    data object FindProfessor : CallbackQuery(PROFESSOR_DATA, PROFESSOR_TEXT)
    data object FindSource : CallbackQuery(SOURCE_DATA, SOURCE_TEXT)
    data class GetNextPage(override val data: String) : CallbackQuery(data, NEXT_PAGE_TEXT)
    data object StopFindSource : CallbackQuery(STOP_FIND_DATA, STOP_FIND_TEXT)
    companion object {
        val findValues = listOf(FindProfessor, FindSource)
        private const val PROFESSOR_DATA = "professor_data"
        private const val SOURCE_DATA = "source_data"
        const val STOP_FIND_DATA = "stop_find_data"
        private const val PROFESSOR_TEXT = "Поиск руководителя"
        const val NEXT_PAGE_TEXT = "Следующая страница"
        private const val SOURCE_TEXT = "Поиск литературы"
        const val STOP_FIND_TEXT = "Осановить поиск"

        fun from(data: String): CallbackQuery {
            return when (data) {
                PROFESSOR_DATA -> FindProfessor
                SOURCE_DATA -> FindSource
                STOP_FIND_DATA -> StopFindSource
                else -> GetNextPage(data)
            }
        }
    }
}