package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface LearningActivityDao {
    @Query("SELECT * FROM activity")
    fun getAll(): List<LearningActivityDB>

    @Transaction
    @Query("SELECT * FROM activity WHERE activityId = :activityId")
    fun getLearningActivityWithQuestions(activityId: Long): List<LearningActivityWithQuestionsDB>
}