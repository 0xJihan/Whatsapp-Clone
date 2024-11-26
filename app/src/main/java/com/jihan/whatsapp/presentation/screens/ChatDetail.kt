package com.jihan.whatsapp.presentation.screens


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jihan.whatsapp.R
import com.jihan.whatsapp.presentation.componenets.ChatBubble
import com.jihan.whatsapp.presentation.componenets.CircularImage
import com.jihan.whatsapp.presentation.componenets.ImageButton
import com.jihan.whatsapp.ui.theme.AppTheme
import com.jihan.whatsapp.ui.theme.primaryColor


@Composable
fun ChatDetailScreen() {


    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(Modifier.fillMaxSize(),
        topBar = {
            ChatTopBar()
        },
        bottomBar = {
            var isError by rememberSaveable { mutableStateOf(false) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                value = text,
                trailingIcon = {
                    ImageButton(Icons.AutoMirrored.Default.Send)
                },
                maxLines = 4,
                onValueChange = {
                    if (it.length <= 3000)
                        text = it

                    isError = it.length > 3000
                },
                placeholder = { Text("Type a message...") },
                isError = isError,

                )
        }

    ) { paddingValues ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(200) {
                ChatBubble("Hey, How are you?", true)
                ChatBubble("Great", false)
            }
        }


    }
}

@Preview
@Composable
private fun ChatPreview() {
    AppTheme {
        ChatDetailScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularImage(R.drawable.profile, 45)
                Spacer(Modifier.width(20.dp))
                Text("Abelson Lucas")
            }
        },
        navigationIcon = {
            ImageButton(
                Icons.AutoMirrored.Default.ArrowBack
            )
        },
        actions = {
            ImageButton(Icons.Default.Call)
            ImageButton(painter = painterResource(R.drawable.baseline_videocam_24))
            ImageButton(Icons.Default.MoreVert)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}