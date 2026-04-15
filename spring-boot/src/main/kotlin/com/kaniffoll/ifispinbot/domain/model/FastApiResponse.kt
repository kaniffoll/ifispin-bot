package com.kaniffoll.ifispinbot.domain.model

data class FastApiResponse(
    val results: List<Professor>
) {
    override fun toString(): String {
        if (results.isEmpty())
            return "Нет подходящих руководителей"

        return results
            .mapIndexed { index, professor -> "${index + 1}. $professor"}
            .joinToString(separator = "\n")
    }
}