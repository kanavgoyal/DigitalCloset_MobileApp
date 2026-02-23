package com.smartcloset.india.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.smartcloset.india.ui.screens.ClosetScreen
import com.smartcloset.india.ui.screens.RecommendationScreen
import com.smartcloset.india.ui.screens.SettingsScreen
import com.smartcloset.india.ui.screens.StorageScreen
import com.smartcloset.india.ui.viewmodel.ClosetViewModel
import com.smartcloset.india.ui.viewmodel.SettingsViewModel

@Composable
fun SmartClosetApp() {
    val navController = rememberNavController()
    val tabs = listOf(Screen.Closet, Screen.Recommend, Screen.Storage, Screen.Settings)

    Scaffold(bottomBar = {
        NavigationBar {
            val backStack by navController.currentBackStackEntryAsState()
            val currentDestination = backStack?.destination
            tabs.forEach { screen ->
                NavigationBarItem(
                    label = { Text(screen.label) },
                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) { padding ->
        NavHost(navController = navController, startDestination = Screen.Closet.route, modifier = Modifier.padding(padding)) {
            composable(Screen.Closet.route) {
                ClosetScreen(hiltViewModel<ClosetViewModel>())
            }
            composable(Screen.Recommend.route) {
                RecommendationScreen(hiltViewModel<ClosetViewModel>())
            }
            composable(Screen.Storage.route) {
                StorageScreen(hiltViewModel<ClosetViewModel>())
            }
            composable(Screen.Settings.route) {
                SettingsScreen(hiltViewModel<SettingsViewModel>())
            }
        }
    }
}
