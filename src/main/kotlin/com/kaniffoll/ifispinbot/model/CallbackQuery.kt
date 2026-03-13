package com.kaniffoll.ifispinbot.model

sealed class CallbackQuery(val data: String, val text: String) {
    data object FindProfessorQuery : CallbackQuery(PROFESSOR_DATA, PROFESSOR_TEXT)
    data object FindSourceQuery: CallbackQuery(SOURCE_DATA, SOURCE_TEXT)

    companion object {
        val values = listOf(FindProfessorQuery, FindSourceQuery)
        val valuesData by lazy { values.map { it.data } }
        private const val PROFESSOR_DATA = "professor_data"
        private const val SOURCE_DATA = "source_data"
        private const val PROFESSOR_TEXT = "Поиск руководителя"
        private const val SOURCE_TEXT = "Поиск литературы"

        fun from(data: String): CallbackQuery {
            return when (data) {
                PROFESSOR_DATA -> FindProfessorQuery
                SOURCE_DATA -> FindSourceQuery
                else -> throw IllegalArgumentException("Unknown callback query: $data")
            }
        }
    }
}