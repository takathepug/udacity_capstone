package com.example.udacity_capstone.data.room

import androidx.room.*


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
)

@Entity(tableName = "activity")
data class LearningActivityDB(
    @PrimaryKey(autoGenerate = true)
    val activityId: Long,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String
)

// Relationship Materials -> Activities
data class LearningMaterialsWithActivities(
    @Embedded val learningMaterials: LearningMaterialsDB,
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
    val correctOption: Int,
    @ColumnInfo(name = "image")
    val image: String
)

// Relationship Activity -> Questions
data class LearningActivityWithQuestions(
    @Embedded val learningActivity: LearningActivityDB,
    @Relation(
        parentColumn = "activityId",
        entityColumn = "questionId"
    ) val questions: List<QuestionDB>
)
