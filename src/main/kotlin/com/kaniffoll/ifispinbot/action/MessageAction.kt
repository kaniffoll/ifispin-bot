package com.kaniffoll.ifispinbot.action

interface MessageAction {
    operator fun invoke(chatId: Long, message: String)
}