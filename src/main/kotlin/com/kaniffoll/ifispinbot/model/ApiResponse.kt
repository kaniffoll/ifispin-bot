package com.kaniffoll.ifispinbot.model

data class ApiResponse(
    val data: List<Paper>
)

data class Paper(
    val title: String,
    val url: String?,
    val publicationDate: String?
) {
    override fun toString(): String {
        return "📕Заголовок: $title,\n📆Дата публикации: $publicationDate\n🌎Ссылка: $url\n"
    }
}