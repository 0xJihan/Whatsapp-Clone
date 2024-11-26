package com.jihan.whatsapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.jihan.whatsapp.presentation.componenets.ChatItem

@Composable
fun ChatsScreen() {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val database = Firebase.firestore
    var userList by remember { mutableStateOf<QuerySnapshot?>(null) }


    LaunchedEffect(Unit) {
        isLoading = true
         database.collection("Users")
            .addSnapshotListener{value,error->
                if (error != null) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                userList = value
                isLoading = false
            }

    }


    LazyColumn(Modifier.fillMaxSize()) {
        items(userList?.documents ?: emptyList()) { user ->

            ChatItem(user)

        }
    }
}