package com.smartcloset.india.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartcloset.india.ui.viewmodel.ClosetViewModel

@Composable
fun ClosetScreen(viewModel: ClosetViewModel) {
    val items by viewModel.items.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("SmartCloset India", style = MaterialTheme.typography.headlineSmall)
        Text("Quick Add: capture → auto-tag → save | Hindi ready: Jaldi add karo")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.quickAddDemo() }) { Text("Quick Add") }
            Button(onClick = { viewModel.generateDailyOutfit() }) { Text("Generate Look") }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text(item.name, style = MaterialTheme.typography.titleMedium)
                        Text("${item.category} • ${item.colors.joinToString()} • worn ${item.wearCount} times")
                        Text("Tags: ${item.tags.joinToString()}")
                    }
                }
            }
        }
    }
}
