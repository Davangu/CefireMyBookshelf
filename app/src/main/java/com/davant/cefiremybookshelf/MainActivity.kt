package com.davant.cefiremybookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.davant.cefiremybookshelf.navigation.NavigationWrapper
import com.davant.cefiremybookshelf.ui.theme.CefireMyBookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CefireMyBookshelfTheme {
                NavigationWrapper()
            }
        }
    }
}