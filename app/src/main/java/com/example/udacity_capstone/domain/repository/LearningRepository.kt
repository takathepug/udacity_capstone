package com.example.udacity_capstone.domain.repository

import com.example.udacity_capstone.domain.model.LearningActivity
import com.example.udacity_capstone.domain.model.LearningMaterials

interface LearningRepository {
    fun getMaterials(): List<LearningMaterials>
    fun getActivities(): List<LearningActivity>
}