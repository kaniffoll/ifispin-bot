package com.kaniffoll.ifispinbot.domain.model.action

interface Action<in I, out O> {
    operator fun invoke(input: I): O
}