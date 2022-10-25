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
    abstract suspend fun getAllMaterials(): List<LearningMaterialsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMaterials(learningMaterialsDB: LearningMaterialsDB): Long

    // Activities
    @Query("SELECT * FROM activity")
    abstract suspend fun getAllActivities(): List<LearningActivityDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivity(learningActivityDB: LearningActivityDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivities(learningActivityDB: List<LearningActivityDB>): List<Long>

    // Questions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertQuestion(questionDB: QuestionDB): Long

    @Query("SELECT * FROM question")
    abstract suspend fun getAllQuestions(): List<QuestionDB>

    // Complex operations
    @Transaction
    open suspend fun insertDetailedLearningMaterials(
        detailedLearningMaterialsDB: DetailedLearningMaterialsDB): DetailedLearningMaterialsDB {

        val insertedLearningMaterialsId = insertMaterials(
            detailedLearningMaterialsDB.learningMaterialsDB)
        detailedLearningMaterialsDB.learningMaterialsDB.materialsId = insertedLearningMaterialsId

        // set FK materialsid
        for (l in detailedLearningMaterialsDB.activities) {
            l.learningActivityDB.fkMaterialsId = insertedLearningMaterialsId
            val insertedActivityId = insertActivity(l.learningActivityDB)
            l.learningActivityDB.activityId = insertedActivityId
            for (q in l.questions) {
                q.activityId = insertedActivityId
                val insertedQuestionId = insertQuestion(q)
                q.questionId = insertedQuestionId
            }
        }

        return detailedLearningMaterialsDB
    }

/*    @Transaction
    @Query("SELECT * FROM materials m JOIN activity a ON m.materialsid = a.materialsid " +
            "WHERE m.materialsid = :materialsId")
    abstract suspend fun getMaterialsWithActivities(materialsId: Long): Map<LearningMaterialsDB, List<LearningActivityDB>>*/

    @Transaction
    @Query("SELECT * FROM materials m JOIN activity a ON m.materialsid = a.fkmaterialsid " +
            "WHERE m.materialsid = :materialsId")
    abstract suspend fun getMaterialsWithActivities(materialsId: Long):
            Map<LearningMaterialsDB, List<LearningActivityDB>>

/*
    @Transaction
    @Query("SELECT * FROM activity")
    abstract suspend fun getActivitiesWithQuestions(): List<LearningActivityWithQuestionsDB>
*/
/*
    @Query("SELECT * FROM materials m JOIN activity a ON m.materialsid = a.materialsid  WHERE materialsid = :materialsId")
    abstract suspend fun getDetailedLearningMaterials(materialsId: Long): DetailedLearningMaterialsDB

    @Transaction
    @Query("SELECT * FROM activity WHERE activityid = :activityId")
    abstract fun getLearningActivityWithQuestions(activityId: Long): List<LearningActivityWithQuestionsDB>
*/
}
