package com.example.udacity_capstone.domain.model

import java.time.OffsetDateTime


data class LearningMaterials(
    val uuid: Int,
    val creationTime: OffsetDateTime,
    val description: String) {

    val learningActivities: List<LearningActivity> = emptyList()
}

sealed class LearningActivity {
    abstract val uuid: String
    abstract val type: Int
    abstract val name: String
}

data class GivenImageMatchText(
    override val uuid: String,
    override val type: Int,
    override val name: String,
    val image: String,
    val options: List<String> = emptyList(),
    val correctOption: Int
) : LearningActivity()

data class FillInBlanks(
    override val uuid: String,
    override val type: Int,
    override val name: String,
    val sentence: String,
    val correctOption: Int
) : LearningActivity()

data class Image(
    val name: String)

