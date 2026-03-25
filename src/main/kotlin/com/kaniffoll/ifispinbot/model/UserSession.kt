package com.kaniffoll.ifispinbot.model

sealed class UserSession(val id: Int) {
    data object WaitForProfessor: UserSession(WAIT_FOR_PROFESSOR_ID)
    data object WaitForSource: UserSession(WAIT_FOR_SOURCE_ID)

    companion object {
        val valuesMap: Map<Int, UserSession> by lazy {
            mapOf(
                WAIT_FOR_PROFESSOR_ID to WaitForProfessor,
                WAIT_FOR_SOURCE_ID to WaitForSource,
            )
        }
        private const val WAIT_FOR_PROFESSOR_ID = 1
        private const val WAIT_FOR_SOURCE_ID = 2

        fun from(sessionId: Int): UserSession {
            return valuesMap[sessionId]
                ?: throw IllegalArgumentException("Unknown user session ID $sessionId")
        }
    }
}