package com.kaniffoll.ifispinbot.domain.model.upd

sealed class CommandType(val command: String) {
    data object Start : CommandType(START_COMMAND)
    companion object {
        val values: List<CommandType> = listOf(Start)
        val valuesCommands: List<String> by lazy { values.map { it.command } }
        private const val START_COMMAND = "/start"
        fun from(command: String) =
            when (command) {
                START_COMMAND -> Start
                else -> throw IllegalArgumentException("Unknown command: $command")
            }
    }
}