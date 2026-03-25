package com.kaniffoll.ifispinbot.action

interface Action<in I, out O> {
    operator fun invoke(input: I): O
}