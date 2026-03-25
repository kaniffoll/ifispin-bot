package com.kaniffoll.ifispinbot.model

import com.kaniffoll.ifispinbot.action.ActionStringRes.NEXT_PAGE_TEXT

sealed class CallbackQuery(open val data: Any, val text: String) {
    data object FindProfessor : CallbackQuery(PROFESSOR_DATA, PROFESSOR_TEXT)
    data object FindSource : CallbackQuery(SOURCE_DATA, SOURCE_TEXT)
    data class GetNextPage(override val data: GetSourcesCallbackData) : CallbackQuery(data, NEXT_PAGE_TEXT)
    companion object {
        val findValues = listOf(FindProfessor, FindSource)
        private const val PROFESSOR_DATA = "professor_data"
        private const val SOURCE_DATA = "source_data"
        private const val PROFESSOR_TEXT = "Поиск руководителя"
        private const val SOURCE_TEXT = "Поиск литературы"

        fun from(data: String): CallbackQuery {

            return when (val parsedResult = ParsedResult.parse(data)) {
                is ParsedResult.JsonData -> GetNextPage(parsedResult.value)
                is ParsedResult.RowData -> {
                    when (parsedResult.value) {
                        PROFESSOR_DATA -> FindProfessor
                        SOURCE_DATA -> FindSource
                        else -> throw IllegalArgumentException("Unknown callback query: $data")
                    }
                }
            }
        }
    }
}