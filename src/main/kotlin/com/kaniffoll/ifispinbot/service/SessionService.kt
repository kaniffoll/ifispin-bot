package com.kaniffoll.ifispinbot.service

import com.kaniffoll.ifispinbot.model.UserSession
import com.kaniffoll.ifispinbot.repository.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(private val sessionRepository: SessionRepository) {
    fun setState(chatId: Long, session: UserSession) =
        sessionRepository.setSessionId(chatId.toString(), session.id.toString())

    fun getUserSession(chatId: Long): UserSession? {
        sessionRepository.getSessionId(chatId.toString())?.let { sessionId ->
            return UserSession.from(sessionId)
        }
        return null
    }

    fun clear(chatId: Long) =
        sessionRepository.clear(chatId.toString())

    fun setQueryForSources(chatId: Long, query: String) =
        sessionRepository.setQueryForSources(chatId.toString(), query)

    fun getQueryForSources(chatId: Long): String? =
        sessionRepository.getQueryForSources(chatId.toString())
}