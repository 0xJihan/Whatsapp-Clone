package com.jihan.whatsapp.presentation.componenets
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun ChatBubble(
    message: String,
    isSentByUser: Boolean
) {


    val bubbleColor = when{
        isSentByUser && isSystemInDarkTheme() -> Color(0xFF005D4B)
        isSentByUser && !isSystemInDarkTheme() -> Color(0xFFDCF8C6)
        !isSentByUser && !isSystemInDarkTheme() -> Color(0xFFF1F1F1)
        else -> Color(0xFF1F2C34)
    }

    val paddingValues = if(isSentByUser) PaddingValues(
        start = 100.dp,
        end = 10.dp,
        top = 5.dp,
        bottom = 5.dp
    )else PaddingValues(
        start = 10.dp,
        end = 100.dp,
        top = 5.dp,
        bottom = 5.dp
    )

    val alignment = if (isSentByUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleShape = if (isSentByUser) {
        RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp)
    } else {
        RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
    }

    Box(
        contentAlignment = alignment,
        modifier = Modifier.fillMaxWidth()
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .background(bubbleColor, bubbleShape)
                .padding(12.dp)
        ) {
            Text(
                text = message,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}
