package com.example.udacity_capstone.domain.usecase

import com.example.udacity_capstone.domain.model.LearningMaterials
import com.example.udacity_capstone.domain.repository.NewMaterialsRepository

class CacheNewLearningMaterialsUseCaseImpl(
    private val newMaterialsRepository: NewMaterialsRepository
): CacheNewLearningMaterialsUseCase {
    override suspend fun invoke(): Result<List<LearningMaterials>> {
        newMaterialsRepository.cacheNewLearningMaterials()

        // TODO complete
        // return is dummy
        return Result.success(emptyList())
    }
}