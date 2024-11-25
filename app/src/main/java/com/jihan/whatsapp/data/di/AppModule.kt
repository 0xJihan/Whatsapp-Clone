package com.jihan.whatsapp.data.di

import com.jihan.whatsapp.domain.utils.json
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    val jsonConverterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())


}


