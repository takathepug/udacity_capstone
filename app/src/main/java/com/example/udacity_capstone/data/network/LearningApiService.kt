package com.example.udacity_capstone.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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