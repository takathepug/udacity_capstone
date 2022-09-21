package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Query


@Dao
interface LearningActivityDao {
    @Query("SELECT * FROM activity")
    fun getAll(): List<LearningActivityDB>
}