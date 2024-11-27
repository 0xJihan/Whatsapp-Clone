package com.jihan.whatsapp.data.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jihan.whatsapp.domain.utils.json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.converter.kotlinx.serialization.asConverterFactory



val appModule = module {


    val jsonConverterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())


    single {
         Firebase.firestore
    }



    single {
         FirebaseAuth.getInstance()
    }



}


