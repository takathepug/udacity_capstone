package com.example.udacity_capstone.data.room

import androidx.room.Dao
import androidx.room.Query

import com.example.udacity_capstone.domain.model.LearningActivity


@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    fun getAll(): List<LearningActivity>
}