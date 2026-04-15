package com.kaniffoll.ifispinbot.domain.model

data class Professor(
    val name: String,
    val score: Double
) {
    override fun toString(): String {
        return "$name, соответствие теме: ${(score * 100).toInt()}%"
    }
}
