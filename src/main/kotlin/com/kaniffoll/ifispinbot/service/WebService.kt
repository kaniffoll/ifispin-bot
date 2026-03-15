package com.kaniffoll.ifispinbot.service

import com.kaniffoll.ifispinbot.config.ApiConfig
import com.kaniffoll.ifispinbot.model.ApiResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WebService(
    private val webClient: WebClient,
    private val apiConfig: ApiConfig
) {
    fun getSources(query: String, offset: Int = 0, limit: Int = 10): Mono<ApiResponse> =
        webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path(apiConfig.searchPath)
                    .queryParam(QUERY_PARAM_NAME, query)
                    .queryParam(FIELDS_PARAM_NAME, apiConfig.fields)
                    .queryParam(OFFSET_PARAM_NAME, offset)
                    .queryParam(LIMIT_PARAM_NAME, limit)
                    .build()
            }
            .retrieve()
            .bodyToMono(ApiResponse::class.java)

    companion object {
        private const val QUERY_PARAM_NAME = "query"
        private const val OFFSET_PARAM_NAME = "offset"
        private const val LIMIT_PARAM_NAME = "limit"
        private const val FIELDS_PARAM_NAME = "fields"
    }
}