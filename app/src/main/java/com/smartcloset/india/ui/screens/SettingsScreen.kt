package com.smartcloset.india.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartcloset.india.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val provider by viewModel.providerName.collectAsState()
    val sendPhotos by viewModel.sendPhotos.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("AI Assist Settings")
        Text("Current provider: $provider")
        Button(onClick = { viewModel.setProvider("OpenAI") }) { Text("Use OpenAI") }
        Button(onClick = { viewModel.setProvider("Gemini") }) { Text("Use Gemini") }
        Text("Share raw photos with LLM (default off)")
        Switch(checked = sendPhotos, onCheckedChange = viewModel::setPhotoSharing)
        Text("Privacy by design: metadata only by default.")
    }
}
