package com.example.udacity_capstone.data.room

import androidx.room.*


@Entity(tableName = "materials")
data class LearningMaterialsDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "materialsid")
    var materialsId: Long?,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: String,
    @ColumnInfo(name = "description")
    val description: String?,
)

@Entity(
    tableName = "activity",
    foreignKeys = [
        ForeignKey(
            entity = LearningMaterialsDB::class,
            parentColumns = ["materialsid"],
            childColumns = ["materialsid"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LearningActivityDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "activityid")
    var activityId: Long?,
    @ColumnInfo(name = "materialsid", index = true)
    var materialsId: Long?,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String
)

@Entity(
    tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = LearningActivityDB::class,
            parentColumns = ["activityid"],
            childColumns = ["activityid"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionid")
    var questionId: Long?,
    @ColumnInfo(name = "activityid", index = true)
    var activityId: Long?,
    @ColumnInfo(name = "options")
    val options: List<String>,
    @ColumnInfo(name = "correctoption")
    val correctOption: Int,
    @ColumnInfo(name = "image")
    val image: String
)

// Composition of entities
data class DetailedLearningMaterialsDB(
    val learningMaterialsDB: LearningMaterialsDB,
    val activities: List<DetailedLearningActivityDB>
)

data class DetailedLearningActivityDB(
    val learningActivityDB: LearningActivityDB,
    val questions: List<QuestionDB>
)

// ##################
/*
data class LearningMaterialsWithActivitiesDB(
    @Embedded
    var learningMaterialsDB: LearningMaterialsDB,
    @Relation(
        parentColumn = "materialsid",
        entityColumn = "materialsid"
    )
    val questions: List<LearningActivityWithQuestionsDB>
) {
    constructor() : this(LearningMaterialsDB(null, "", "", ""),
        emptyList())
}

data class LearningActivityWithQuestionsDB(
    @Embedded
    val learningActivityDB: LearningActivityDB,
    @Relation(
        parentColumn = "activityid",
        entityColumn = "activityid",
        entity = QuestionDB::class
    )
    val questions: List<QuestionDB>
)*/
