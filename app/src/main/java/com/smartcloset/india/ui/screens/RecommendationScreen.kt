package com.smartcloset.india.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartcloset.india.domain.model.Occasion
import com.smartcloset.india.ui.viewmodel.ClosetViewModel

@Composable
fun RecommendationScreen(viewModel: ClosetViewModel) {
    val suggestion by viewModel.suggestion.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Look smart every time", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { viewModel.generateDailyOutfit(Occasion.OFFICE, astroOn = true) }) {
            Text("Generate (Astro color on)")
        }
        Button(onClick = { viewModel.generateDailyOutfit(Occasion.TRAVEL, astroOn = false) }) {
            Text("Trip Packing List")
        }

        suggestion?.let {
            Card(Modifier.padding(top = 8.dp)) {
                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Top: ${it.top?.name ?: "N/A"}")
                    Text("Bottom: ${it.bottom?.name ?: "N/A"}")
                    Text("Footwear: ${it.footwear?.name ?: "N/A"}")
                    Text("Layer: ${it.layers.joinToString { item -> item.name }}")
                    Text("Accessories: ${it.accessories.joinToString { item -> item.name }}")
                    Text("Confidence: ${it.confidence}%")
                    Text("Why this works: ${it.explanation}")
                }
            }
        }
    }
}
