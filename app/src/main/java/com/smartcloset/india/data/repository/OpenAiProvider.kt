package com.smartcloset.india.data.repository

import com.smartcloset.india.data.remote.LlmApi
import com.smartcloset.india.data.remote.LlmMessage
import com.smartcloset.india.data.remote.LlmRequest
import com.smartcloset.india.domain.repository.LlmProvider

class OpenAiProvider(
    private val api: LlmApi,
    private val model: String = "gpt-4o-mini"
) : LlmProvider {
    override suspend fun ask(prompt: String): String {
        val response = api.chat(LlmRequest(model = model, messages = listOf(LlmMessage("user", prompt))))
        return response.choices.firstOrNull()?.message?.content ?: "No response"
    }
}
