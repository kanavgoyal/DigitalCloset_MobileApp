package com.smartcloset.india.data.repository

import com.smartcloset.india.domain.repository.LlmProvider

class GeminiProvider : LlmProvider {
    override suspend fun ask(prompt: String): String {
        return "Gemini placeholder response for: $prompt"
    }
}
