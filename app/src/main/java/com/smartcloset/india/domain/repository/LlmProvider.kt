package com.smartcloset.india.domain.repository

interface LlmProvider {
    suspend fun ask(prompt: String): String
}
