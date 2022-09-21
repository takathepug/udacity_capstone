package com.example.udacity_capstone.domain.usecase

import com.example.udacity_capstone.domain.model.LearningMaterials

interface CacheNewDetailedLearningUnitsUseCase {
    operator fun invoke(): Result<List<LearningMaterials>>
}