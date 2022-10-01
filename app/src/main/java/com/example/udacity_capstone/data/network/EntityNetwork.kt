package com.example.udacity_capstone.data.network

import com.example.udacity_capstone.data.room.LearningActivityDB
import com.example.udacity_capstone.data.room.LearningMaterialsDB
import com.example.udacity_capstone.data.room.QuestionDB
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

fun LearningMaterialsDTO.asDB(): LearningMaterialsDB {
    return LearningMaterialsDB(
null, uuid, creationTime, description
    )
}

@JsonClass(generateAdapter = true)
data class LearningActivityDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "questions")
    val questions: List<QuestionDTO>
)

fun LearningActivityDTO.asDB(): LearningActivityDB {
    return LearningActivityDB(
        null, null, uuid, name
    )
}

@JsonClass(generateAdapter = true)
data class QuestionDTO(
    @Json(name = "image")
    val image: String,
    @Json(name = "options")
    val options: List<String>,
    @Json(name = "correct_option")
    val correctOption: Int
)

fun QuestionDTO.asDB(): QuestionDB {
    return QuestionDB(
        null, null, options, correctOption, image
    )
}
