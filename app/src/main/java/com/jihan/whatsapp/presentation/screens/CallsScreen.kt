package com.jihan.whatsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CallsScreen(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxSize().background(color = Color.Cyan), contentAlignment = Alignment.Center){
        Text("Calls Screen", fontSize = 40.sp)
    }
}