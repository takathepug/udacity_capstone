package com.example.udacity_capstone.domain.repository

/**
 * New learning materials repository
 */
interface NewMaterialsRepository {
    suspend fun refreshMaterials(startDate: String)
}