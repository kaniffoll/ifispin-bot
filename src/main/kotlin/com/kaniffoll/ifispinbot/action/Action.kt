package com.kaniffoll.ifispinbot.action

interface Action {
    operator fun invoke(chatId: Long)
}