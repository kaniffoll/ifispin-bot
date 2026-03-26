package com.kaniffoll.ifispinbot.domain.service

import com.kaniffoll.ifispinbot.data.config.ApiConfig
import com.kaniffoll.ifispinbot.domain.model.ApiResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WebService(
    private val webClient: WebClient,
    private val apiConfig: ApiConfig
) {
    fun getSources(query: String, page: Int = 1, perPage: Int = 10): Mono<ApiResponse> =
        webClient.get()
            .uri { uriBuilder ->
                val uri = uriBuilder
                    .path(apiConfig.path)
                    .queryParam(apiConfig.apiKeyParam, apiConfig.apiKey)
                    .queryParam(apiConfig.searchParam, query)
                    .queryParam(apiConfig.filterParam, FILTER_PARAMS)
                    .queryParam(apiConfig.selectParam, SELECT_PARAMS)
                    .queryParam(apiConfig.pageParam, page)
                    .queryParam(apiConfig.perPageParam, perPage)
                    .build()
                uri
            }
            .retrieve()
            .bodyToMono(ApiResponse::class.java)

    companion object {
        private const val FILTER_PARAMS = "language:en|ru"
        private const val SELECT_PARAMS =
            "id,doi,display_name,cited_by_count,language,publication_year"
    }

}