package com.example.udacity_capstone.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.udacity_capstone.domain.model.LearningActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime

// TODO https://developer.android.com/training/data-storage/room/referencing-data#type-converters
@Entity(tableName = "materials")
data class LearningMaterialsDB(
    @PrimaryKey(autoGenerate = true)
    val materialsId: Long,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: String,
    @ColumnInfo(name = "description")
    val description: String?
    // TODO FK val learningActivities: List<LearningActivity> = emptyList()
)

@Entity(tableName = "activity")
data class LearningActivityDB(
    @PrimaryKey(autoGenerate = true)
    val activityId: Long,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String
    // TODO FK Materials
)

data class LearningMaterialsWithActivities(
    @Embedded val materials: LearningMaterialsDB,
    @Relation(
        parentColumn = "materialsId",
        entityColumn = "activityId"
    ) val activities: List<LearningActivityDB>
)

@Entity(tableName = "question")
data class QuestionDB(
    @PrimaryKey(autoGenerate = true)
    val questionId: Long,
    @ColumnInfo(name = "options")
    val options: List<String>,
    @ColumnInfo(name = "correct_option")
    val correctOption: Int
    // TODO FK ImageId
    // TODO FK Activity
)

@Entity(tableName = "image")
data class ImageDB(
    @PrimaryKey
    val imageId: Long,
    @ColumnInfo(name = "image")
    val image: String
    // TODO BLOB pic
)
