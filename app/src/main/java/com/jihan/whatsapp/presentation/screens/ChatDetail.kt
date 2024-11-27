package com.jihan.whatsapp.presentation.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.jihan.whatsapp.R
import com.jihan.whatsapp.data.models.Message
import com.jihan.whatsapp.presentation.componenets.ChatBubble
import com.jihan.whatsapp.presentation.componenets.CircularImage
import com.jihan.whatsapp.presentation.componenets.ImageButton
import com.jihan.whatsapp.presentation.destinations.Destination
import com.jihan.whatsapp.ui.theme.primaryColor


@Composable
fun ChatDetailScreen(chatDetail: Destination.ChatDetail) {
    val context = LocalContext.current

    val senderRoom = chatDetail.senderId+chatDetail.receiverId
    val receiverRoom = chatDetail.receiverId+chatDetail.senderId

    val firestore = Firebase.firestore
    val senderDatabase = firestore.collection("chats").document(senderRoom).collection("messages")
    val receiverDatabase = firestore.collection("chats").document(receiverRoom).collection("messages")

    var message by rememberSaveable { mutableStateOf("") }

    fun sendMessage(msg:String){
        senderDatabase.add(Message(msg,chatDetail.senderId))
        receiverDatabase.add(Message(msg,chatDetail.senderId))
        message = ""
    }

    var messagesList  by remember {
        mutableStateOf(listOf<Message>())
    }




    LaunchedEffect(Unit) {
     senderDatabase.orderBy("timeStamp")
         .addSnapshotListener{value,error->
         if (error!=null){
             Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()
             return@addSnapshotListener
         }
         messagesList = value?.toObjects(Message::class.java) ?: emptyList()

     }
    }




    Scaffold(
        Modifier
            .fillMaxSize(),
        topBar = {
            ChatTopBar(chatDetail.receiverName)
        },

    ) { paddingValues ->

        Box(Modifier.fillMaxSize().padding(paddingValues)){
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .imePadding()
            ) {
                if (messagesList.isEmpty()) {
                    item {
                        Text("No messages yet",modifier = Modifier.padding(10.dp))
                    }
                }
                else
                    items(messagesList) {
                        ChatBubble(it.message!!,it.uid==chatDetail.senderId)

                    }
            }

            //==========
            var isError by rememberSaveable { mutableStateOf(false) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 4.dp),
                value = message,
                trailingIcon = {
                    ImageButton(Icons.AutoMirrored.Default.Send){
                        if (message.isNotEmpty()){
                           sendMessage(message)
                        }
                    }
                },
                maxLines = 3,
                onValueChange = {
                    if (it.length <= 3000)
                        message = it

                    isError = it.length > 3000
                },
                placeholder = { Text("Type a message...") },
                isError = isError,
            )

        }








    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopBar(name:String) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularImage(R.drawable.profile, 45)
                Spacer(Modifier.width(20.dp))
                Text(name, fontSize = 18.sp)
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