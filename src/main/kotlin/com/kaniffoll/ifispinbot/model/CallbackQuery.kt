package com.kaniffoll.ifispinbot.model

sealed class CallbackQuery(val data: String, val text: String) {
    data object FindProfessor : CallbackQuery(PROFESSOR_DATA, PROFESSOR_TEXT)
    data object FindSource: CallbackQuery(SOURCE_DATA, SOURCE_TEXT)

    companion object {
        val values = listOf(FindProfessor, FindSource)
        val valuesData by lazy { values.map { it.data } }
        private const val PROFESSOR_DATA = "professor_data"
        private const val SOURCE_DATA = "source_data"
        private const val PROFESSOR_TEXT = "Поиск руководителя"
        private const val SOURCE_TEXT = "Поиск литературы"

        fun from(data: String): CallbackQuery {
            return when (data) {
                PROFESSOR_DATA -> FindProfessor
                SOURCE_DATA -> FindSource
                else -> throw IllegalArgumentException("Unknown callback query: $data")
            }
        }
    }
}