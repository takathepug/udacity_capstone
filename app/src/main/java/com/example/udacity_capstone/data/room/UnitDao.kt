package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Query

import com.example.udacity_capstone.domain.model.LearningUnit


@Dao
interface UnitDao {
    @Query("SELECT * FROM unit")
    fun getAll(): List<LearningUnit>
}