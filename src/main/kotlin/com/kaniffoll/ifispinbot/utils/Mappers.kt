package com.kaniffoll.ifispinbot.utils

import com.kaniffoll.ifispinbot.model.Paper

fun List<Paper>.toTelegramMessageStringList(): List<String> {
    val result = mutableListOf("")
    val limit = 4096
    var index = 0
    forEach { paper ->
        val currentStr = paper.toString()
        if ((result[index] + currentStr).length < limit)
            result[index] += currentStr
        else {
            index++
            result.add(currentStr)
        }
    }
    return result
}