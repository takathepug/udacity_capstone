package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
abstract class LearningMaterialsDao {
    @Query("SELECT * FROM materials")
    abstract fun getAll(): List<LearningMaterialsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(learningMaterialsDB: LearningMaterialsDB): Long
}
