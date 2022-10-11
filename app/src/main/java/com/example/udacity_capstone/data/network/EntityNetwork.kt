package com.example.udacity_capstone.data.network

import com.example.udacity_capstone.data.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LearningMaterialsDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "creation_timestamp")
    val creationTime: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "activities")
    val activities: List<LearningActivityDTO>
)

@JsonClass(generateAdapter = true)
data class LearningActivityDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "questions")
    val questions: List<QuestionDTO>
)

@JsonClass(generateAdapter = true)
data class QuestionDTO(
    @Json(name = "image")
    val image: String,
    @Json(name = "options")
    val options: List<String>,
    @Json(name = "correct_option")
    val correctOption: Int
)

// extension functions for mapping
fun LearningMaterialsDTO.asDB(): DetailedLearningMaterialsDB {
    return DetailedLearningMaterialsDB(
        LearningMaterialsDB(null, uuid, creationTime, description),
        activities.asDB()
    )
}

fun LearningActivityDTO.asDB(): LearningActivityDB {
    return LearningActivityDB(
        null, null, uuid, name
    )
}

fun List<LearningActivityDTO>.asDB(): List<DetailedLearningActivityDB> {
    return map { a ->
        DetailedLearningActivityDB(
            a.asDB(),
            a.questions.map { o -> o.asDB() }
        )
    }
}

fun QuestionDTO.asDB(): QuestionDB {
    return QuestionDB(
        null, null, options, correctOption, image
    )
}
