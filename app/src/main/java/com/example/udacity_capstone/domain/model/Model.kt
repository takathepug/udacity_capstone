package com.example.udacity_capstone.domain.model


data class LearningMaterials(val materialsId: Long, val uuid: String, val creationTime: String,
                             val description: String?)

data class LearningActivity(val activityId: Long, val uuid: String, val name: String)

data class Question(val questionId: Long, val options: List<String>, val correctOption: Int,
                    val image: String)
