package com.kaniffoll.ifispinbot.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse(
    val meta: Meta,
    val results: List<Work>,
)

data class Meta(
    val count: Int,
    val page: Int,
    @JsonProperty("per_page")
    val perPage: Int,
)

data class Work(
    val id: String,
    val doi: String?,
    @JsonProperty("display_name")
    val displayName: String?,
    @JsonProperty("cited_by_count")
    val citedByCount: Int?,
    val language: String?,
    @JsonProperty("publication_year")
    val publicationYear: String?,
) {
    override fun toString(): String {
        return """
            
            📕Заголовок: ${displayName?:"нет"}
            📆Год публикации: ${publicationYear?:"нет"}
            ✍️Количество цитирований: ${citedByCount?:"нет"}
            ${if (language == "ru") "🇷🇺" else "🇬🇧"} Язык: ${language?:"не указан"}
            🌎Ссылка: ${doi?:"нет"}
            🌎Ссылка 2: $id 
            ----------------------------------------------------------------
        """.trimIndent()
    }
}

fun ApiResponse.toTelegramMessageStringList(): List<String> {
    val result = mutableListOf("Найдено ${this.meta.count} работ, страница: ${this.meta.page}\n")
    val limit = 4096
    var index = 0
    this.results.forEach { work ->
        val currentStr = work.toString()
        if ((result[index] + currentStr).length < limit)
            result[index] += currentStr
        else {
            index++
            result.add(currentStr)
        }
    }
    return result
}