package com.kaniffoll.ifispinbot.model

sealed class CommandType(val command: String) {
    data object Start : CommandType(START_COMMAND)
    data object Help : CommandType(HELP_COMMAND)
    companion object {
        val values: List<CommandType> = listOf(Start, Help)
        val valuesCommands: List<String> by lazy { values.map { it.command } }
        private const val START_COMMAND = "/start"
        private const val HELP_COMMAND = "/help"
        fun from(command: String) =
            when (command) {
                START_COMMAND -> Start
                HELP_COMMAND -> Help
                else -> throw IllegalArgumentException("Unknown command: $command")
            }
    }
}