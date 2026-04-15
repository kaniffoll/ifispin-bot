package com.kaniffoll.ifispinbot.app.res

object ActionStringRes {
    const val START_MESSAGE = "👋 Привет! Я IFiSPN Helper — помогаю находить научную литературу и подбирать научного руководителя.\n" +
            "\n" +
            "Просто выберете интересующую вас опцию и введите текст, и я займусь поиском."
    const val FIND_PROFESSOR_MESSAGE = "Введите название своей работы:"
    const val FIND_SOURCE_MESSAGE = "Введите ключевые слова для поиска литературы:"
    const val WRONG_REQ_MESSAGE = "⚠️ Что-то пошло не так — сессия устарела или запрос не удалось обработать.\n" +
            "\n" +
            "Попробуйте начать заново: просто перезапустите бота."
    const val GET_RESOURCES_WAITING_MESSAGE = "Запрос получен..."
    const val FIND_STOP_MESSAGE = "Поиск остановлен"
    const val SUCCESS_FIND_PROFESSORS_MESSAGE = "Нашёл несколько подходящих научных руководителей по вашему запросу:"
}