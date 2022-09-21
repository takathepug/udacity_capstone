package com.example.udacity_capstone.data.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface LearningMaterialsDao {
    @Query("SELECT * FROM materials")
    fun getAll(): LiveData<List<LearningMaterialsDB>>
}
