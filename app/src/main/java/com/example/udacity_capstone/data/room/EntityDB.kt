package com.example.udacity_capstone.data.room

import androidx.room.*


// https://stackoverflow.com/questions/53301274/room-insert-one-to-many-relation
// https://gist.github.com/svasquezm/401e88bbacd16263d83aacaa0d7c45b7

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
    val description: String?
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

// Relationships
// Relationship Materials -> Activities
data class LearningMaterialsWithActivitiesDB(
    @Embedded
    val learningMaterials: LearningMaterialsDB,
    @Relation(
        parentColumn = "materialsid",
        entityColumn = "activityid"
    )
    val activities: List<LearningActivityWithQuestionsDB>
)

// Relationship Activity -> Questions
data class LearningActivityWithQuestionsDB(
    @Embedded
    val learningActivity: LearningActivityDB,
    @Relation(
        parentColumn = "activityid",
        entityColumn = "questionid"
    )
    val questions: List<QuestionDB>
)