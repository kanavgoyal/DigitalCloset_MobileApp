package com.smartcloset.india.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface LlmApi {
    @POST("v1/chat/completions")
    suspend fun chat(@Body request: LlmRequest): LlmResponse
}

data class LlmRequest(
    val model: String,
    val messages: List<LlmMessage>
)

data class LlmMessage(
    val role: String,
    val content: String
)

data class LlmResponse(
    val choices: List<LlmChoice>
)

data class LlmChoice(
    val message: LlmMessage
)
