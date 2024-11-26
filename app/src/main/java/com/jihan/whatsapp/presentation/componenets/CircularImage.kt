package com.jihan.whatsapp.presentation.componenets

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CircularImage(model:Any,size:Int,modifier: Modifier = Modifier) {

    val borderColor = MaterialTheme.colorScheme.onSurface

    AsyncImage(
        model=model,
        contentDescription = "Circular Image",
        modifier.clip(CircleShape)
            .size(size.dp).drawBehind {
                drawCircle(borderColor)
            }
    )
}
