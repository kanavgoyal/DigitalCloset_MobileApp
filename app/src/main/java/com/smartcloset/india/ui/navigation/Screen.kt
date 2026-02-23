package com.smartcloset.india.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Closet : Screen("closet", "Closet", Icons.Default.Checkroom)
    data object Recommend : Screen("recommend", "Looks", Icons.Default.Lightbulb)
    data object Storage : Screen("storage", "Storage", Icons.Default.Warehouse)
    data object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}
