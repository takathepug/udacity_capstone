package com.example.udacity_capstone.data.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface LearningApiService {
    companion object {
        const val REST_BASE_URL = "https://capstone-learning.herokuapp.com/"
        const val IMAGES_BASE_URL = "https://udacitycapstone.pages.dev/"
    }

    @GET("materials/{user_id}")
    suspend fun getLearningMaterials(
        @Path("user_id") userId: Int
    ): LearningMaterialsDTO
}

object LearningMaterialsApi {
    private var okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private val moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LearningApiService.REST_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    val api: LearningApiService by lazy {
        retrofit
            .create(LearningApiService::class.java)
    }
}