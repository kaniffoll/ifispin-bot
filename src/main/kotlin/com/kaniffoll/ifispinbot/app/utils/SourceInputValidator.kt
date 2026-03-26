package com.kaniffoll.ifispinbot.app.utils

object SourceInputValidator {
    fun validateInput(input: String): ValidatorResponse {
        when {
            input.isBlank() -> return createWrongResponse(EMPTY_INPUT_MESSAGE)
            input.startsWith("/") -> return createWrongResponse(COMMAND_MESSAGE)
        }
        return ValidatorResponse.OK
    }

    private fun createWrongResponse(msg: String) =
        ValidatorResponse.WrongInput("$MESSAGE_PREFIX$msg")

    private const val MESSAGE_PREFIX = "Некорректные данные: "
    private const val EMPTY_INPUT_MESSAGE = "вы ввели пустой текст"
    private const val COMMAND_MESSAGE = "чтобы ввести команду, остановите поиск"
}

sealed interface ValidatorResponse {
    data class WrongInput(val msg: String) : ValidatorResponse
    data object OK: ValidatorResponse
}