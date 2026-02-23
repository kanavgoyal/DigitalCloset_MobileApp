package com.smartcloset.india

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.smartcloset.india.ui.navigation.SmartClosetApp
import com.smartcloset.india.ui.theme.SmartClosetTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartClosetTheme {
                SmartClosetApp()
            }
        }
    }
}
