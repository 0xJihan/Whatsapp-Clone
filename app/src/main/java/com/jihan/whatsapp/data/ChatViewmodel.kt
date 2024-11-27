package com.jihan.whatsapp.data

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ChatViewmodel (
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel(){

    val currentUser = auth.currentUser



}