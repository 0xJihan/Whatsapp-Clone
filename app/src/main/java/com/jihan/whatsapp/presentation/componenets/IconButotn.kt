package com.jihan.whatsapp.presentation.componenets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import coil3.compose.ImagePainter

@Composable
fun ImageButton(imageVector: ImageVector,modifier: Modifier=Modifier, onClick: () -> Unit={}) {
    IconButton(onClick) { Icon(imageVector,null,modifier) }

}

@Composable
fun ImageButton(painter: Painter,modifier: Modifier=Modifier, onClick: () -> Unit={}) {
    IconButton(onClick) { Icon(painter,null,modifier) }

}