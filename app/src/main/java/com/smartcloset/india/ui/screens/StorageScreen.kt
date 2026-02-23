package com.smartcloset.india.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartcloset.india.ui.viewmodel.ClosetViewModel

@Composable
fun StorageScreen(viewModel: ClosetViewModel) {
    val plan by viewModel.storagePlan.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Closet Storage Helper", style = MaterialTheme.typography.headlineSmall)
        Text("Rule: high frequency items => easy access; seasonal => top shelf; delicate => drawers")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(plan.entries.toList()) { entry ->
                Text("${entry.key}: ${entry.value.joinToString()}")
            }
        }
    }
}
