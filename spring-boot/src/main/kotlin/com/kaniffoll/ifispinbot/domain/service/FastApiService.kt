package com.kaniffoll.ifispinbot.domain.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import com.kaniffoll.ifispinbot.data.config.FastApiConfig
import com.kaniffoll.ifispinbot.domain.model.FastApiResponse
import reactor.core.publisher.Mono

@Service
class FastApiService(
    private val webClient: WebClient,
    private val fastApiConfig: FastApiConfig
) {
    fun getProfessors(query: String): Mono<FastApiResponse> {
        val address = "${fastApiConfig.host}:${fastApiConfig.port}"
        return webClient.post()
            .uri("http://$address$PATH_SEARCH_PARAM")
            .bodyValue(
                SearchRequest(query) 
            )
            .retrieve()
            .bodyToMono(FastApiResponse::class.java)
    }

    companion object {
        private const val PATH_SEARCH_PARAM = "/search"
    }
}

private data class SearchRequest(
    val text: String
)
