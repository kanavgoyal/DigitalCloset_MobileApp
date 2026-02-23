package com.smartcloset.india.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.smartcloset.india.data.repository.GeminiProvider
import com.smartcloset.india.data.repository.OpenAiProvider
import com.smartcloset.india.data.remote.LlmApi
import com.smartcloset.india.domain.repository.LlmProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val llmApi: LlmApi
) : ViewModel() {
    private val _providerName = MutableStateFlow("None")
    val providerName: StateFlow<String> = _providerName.asStateFlow()

    private val _sendPhotos = MutableStateFlow(false)
    val sendPhotos: StateFlow<Boolean> = _sendPhotos.asStateFlow()

    var llmProvider: LlmProvider? = null
        private set

    fun setProvider(provider: String) {
        llmProvider = when (provider) {
            "OpenAI" -> OpenAiProvider(llmApi)
            "Gemini" -> GeminiProvider()
            else -> null
        }
        _providerName.value = provider
    }

    fun setPhotoSharing(enabled: Boolean) {
        _sendPhotos.value = enabled
    }
}
