package com.jihan.whatsapp.presentation.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.DocumentSnapshot
import com.jihan.whatsapp.R

@Composable
fun ChatItem(user: DocumentSnapshot) {

    val name = user.getString("name")
    val email = user.getString("email")

    Row(Modifier.fillMaxWidth()) {
    CircularImage(R.drawable.ic_launcher_foreground,100)

        Spacer(Modifier.width(16.dp))

        Column(Modifier.weight(.9f).height(100.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            Text(name?:"", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(email?:"", style = MaterialTheme.typography.bodyLarge)
        }

    }

}