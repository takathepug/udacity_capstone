package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction


// https://www.androidbugfix.com/2022/01/cascade-delete-in-android-room-database.html
@Dao
abstract class LearningMaterialsDao {
    // Materials
    @Query("SELECT * FROM materials")
    abstract fun getAllMaterials(): List<LearningMaterialsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMaterials(learningMaterialsDB: LearningMaterialsDB): Long

    // Activities
    @Query("SELECT * FROM activity")
    abstract fun getAllActivities(): List<LearningActivityDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertActivity(learningActivityDB: LearningActivityDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertActivities(learningActivityDB: List<LearningActivityDB>): List<Long>

    // Questions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertQuestion(questionDB: QuestionDB): Long

    @Query("SELECT * FROM question")
    abstract fun getAllQuestions(): List<QuestionDB>

    // Complex operations
    @Transaction
    open fun insertDetailedLearningMaterials(
        learningMaterialsWithActivitiesDB: LearningMaterialsWithActivitiesDB): LearningMaterialsWithActivitiesDB {

        val insertedLearningMaterialsId = insertMaterials(learningMaterialsWithActivitiesDB.learningMaterials)
        learningMaterialsWithActivitiesDB.learningMaterials.materialsId = insertedLearningMaterialsId

        // set FK materialsid
        for (l in learningMaterialsWithActivitiesDB.activities) {
            val learningActivity: LearningActivityDB = l.learningActivity
            learningActivity.materialsId = insertedLearningMaterialsId
            val insertedActivityId = insertActivity(learningActivity)
            learningActivity.activityId = insertedActivityId
            for (q in l.questions) {
                q.activityId = insertedActivityId
                val insertedQuestionId = insertQuestion(q)
                q.questionId = insertedQuestionId
            }
        }

        return learningMaterialsWithActivitiesDB
    }

    @Transaction
    @Query("SELECT * FROM activity WHERE activityId = :activityId")
    abstract fun getLearningActivityWithQuestions(activityId: Long): List<LearningActivityWithQuestionsDB>
}
