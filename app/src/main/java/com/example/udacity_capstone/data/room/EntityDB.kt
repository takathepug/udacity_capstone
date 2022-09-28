package com.example.udacity_capstone.data.room

import androidx.room.*


// https://stackoverflow.com/questions/53301274/room-insert-one-to-many-relation

@Entity(tableName = "materials")
data class LearningMaterialsDB(
    @PrimaryKey(autoGenerate = true)
    var materialsId: Long?,
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
    val activityId: Long?,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String
)

@Entity(tableName = "question")
data class QuestionDB(
    @PrimaryKey(autoGenerate = true)
    val questionId: Long?,
    @ColumnInfo(name = "options")
    val options: List<String>,
    @ColumnInfo(name = "correct_option")
    val correctOption: Int,
    @ColumnInfo(name = "image")
    val image: String
)

// Relationships
// Relationship Materials -> Activities
data class LearningMaterialsWithActivitiesDB(
    @Embedded val learningMaterials: LearningMaterialsDB,
    @Relation(
        parentColumn = "materialsId",
        entityColumn = "activityId"
    ) val activities: List<LearningActivityWithQuestionsDB>
)

// Relationship Activity -> Questions
data class LearningActivityWithQuestionsDB(
    @Embedded val learningActivity: LearningActivityDB,
    @Relation(
        parentColumn = "activityId",
        entityColumn = "questionId"
    ) val questions: List<QuestionDB>
)