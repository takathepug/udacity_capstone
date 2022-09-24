package com.example.udacity_capstone.data

import com.example.udacity_capstone.data.network.LearningMaterialsApi
import com.example.udacity_capstone.domain.repository.NewMaterialsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class NewMaterialsRepositoryApiImpl: NewMaterialsRepository {
    override suspend fun cacheNewLearningMaterials() {
        withContext(Dispatchers.IO) {
            try {
                val response = LearningMaterialsApi.api.getLearningMaterials(123456)
                Timber.d(response.toString())
                // TODO Save to DB
                // TODO Get userId from session
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}