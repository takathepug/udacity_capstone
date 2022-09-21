package com.example.udacity_capstone.data

import com.example.udacity_capstone.domain.repository.NewMaterialsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class NewMaterialsRepositoryApiImpl: NewMaterialsRepository {
    override suspend fun refreshMaterials(startDate: String) {
        withContext(Dispatchers.IO) {
            try {
                TODO("Not yet implemented")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}